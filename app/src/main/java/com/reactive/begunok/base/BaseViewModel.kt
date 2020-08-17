package com.reactive.begunok.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.reactive.begunok.R
import com.reactive.begunok.network.*
import com.reactive.begunok.network.models.CategoryData
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.extensions.loge
import com.reactive.begunok.utils.extensions.logi
import com.reactive.begunok.utils.extensions.toast
import com.reactive.begunok.utils.network.Errors
import com.reactive.begunok.utils.preferences.SharedManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import retrofit2.HttpException

open class BaseViewModel(
    private val gson: Gson,
    private val context: Context,
    private val sharedManager: SharedManager
) : ViewModel(), KoinComponent {

    @LayoutRes
    var parentLayoutId: Int = 0

    @LayoutRes
    var navLayoutId: Int = 0

    @LayoutRes
    var authLayoutId: Int = 0

    val data: MutableLiveData<Any> by inject()
    val shared: MutableLiveData<Any> by inject(named("shared"))
    val error: MutableLiveData<ErrorResp> by inject(named("error"))
    val user: MutableLiveData<User> by inject(named("user"))
    val categories: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))
    val subCategories: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))
    val jobTypes: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))

    private val compositeDisposable = CompositeDisposable()

    private val api = RetrofitClient
        .getRetrofit(Constants.BASE_URL, sharedManager, context, gson)
        .create(ApiInterface::class.java)

    private fun parseError(e: Throwable?) {
        var message = context.resources.getString(R.string.smth_wrong)
        if (e != null && e.localizedMessage != null) {
            loge(e.localizedMessage)
            if (e is HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                errorBody?.let {
                    try {
                        loge(it)
                        val errors = it.split(":")
                            .filter { it.contains("[") }
                        val errorsString = if (errors.isNotEmpty()) {
                            errors.toString()
                                .replace("[", "")
                                .replace(",", "\n")
                                .replace("]", "")
                                .replace("{", "")
                                .replace("}", "")
                                .replace("\"", "")
                        } else {
                            val resp = it.split(":")
                            if (resp.size >= 2) resp[1].replace("{", "")
                                .replace("}", "")
                                .replace("\"", "")
                            else it
                        }

                        message = if (errorsString.isEmpty())
                            context.resources.getString(R.string.smth_wrong)
                        else errorsString

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else message = Errors.traceErrors(e, context)
        }
        toast(context, message)
        error.value = ErrorResp(message)
    }

    fun fetchData() {

        if (sharedManager.token.isNotEmpty()) {
            logi("Current token : " + sharedManager.token)

            getUser()
            getCategories()
        }
    }

    fun getUser() = compositeDisposable.add(
        api.getUser().observeAndSubscribe()
            .subscribe({
                sharedManager.user = it
                user.value = it
            }, {
                parseError(it)
            })
    )

    fun logout() {
        sharedManager.deleteAll()

    }

    fun login(email: String, password: String) = compositeDisposable.add(
        api.login(email, password).observeAndSubscribe()
            .subscribe({
                sharedManager.token = it.access_token
                fetchData()
            }, {
                parseError(it)
            })
    )

    fun register(body: RegisterRequest) =
        compositeDisposable.add(
            api.register(body)
                .observeAndSubscribe()
                .subscribe({
                    login(body.email, body.password)
                }, {
                    parseError(it)
                })
        )

    fun edit(body: RegisterRequest, id: Int) =
        compositeDisposable.add(
            api.edit(body, id)
                .observeAndSubscribe()
                .subscribe({
                    getUser()
                }, {
                    parseError(it)
                })
        )

    fun getCategories() = compositeDisposable.add(
        api.getCategories().observeAndSubscribe()
            .subscribe({
                categories.value = ArrayList(it)
            }, {
                parseError(it)
            })
    )

    fun getSubCategories(catId: Int) = compositeDisposable.add(
        api.getSubCategories(catId).observeAndSubscribe()
            .subscribe({
                subCategories.value = ArrayList(it)
            }, {
                parseError(it)
            })
    )

    fun getJobTypes(subCatId: Int) = compositeDisposable.add(
        api.getJobTypes(subCatId).observeAndSubscribe()
            .subscribe({
                jobTypes.value = ArrayList(it)
            }, {
                parseError(it)
            })
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

fun <T> Single<T>.observeAndSubscribe() =
    subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
