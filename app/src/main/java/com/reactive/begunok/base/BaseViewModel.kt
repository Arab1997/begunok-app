package com.reactive.begunok.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reactive.begunok.R
import com.reactive.begunok.network.ApiInterface
import com.reactive.begunok.network.ErrorResp
import com.reactive.begunok.network.RetrofitClient
import com.reactive.begunok.network.User
import com.reactive.begunok.network.models.*
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.EmailData
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.extensions.loge
import com.reactive.begunok.utils.extensions.toast
import com.reactive.begunok.utils.network.Errors
import com.reactive.begunok.utils.preferences.SharedManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import retrofit2.HttpException
import java.io.File

open class BaseViewModel(
    private val context: Context,
    private val sharedManager: SharedManager
) : ViewModel(), KoinComponent {

    @LayoutRes
    var parentLayoutId: Int = 0

    @LayoutRes
    var navLayoutId: Int = 0

    val data: MutableLiveData<Any> by inject()
    val shared: MutableLiveData<Any> by inject(named("shared"))
    val error: MutableLiveData<ErrorResp> by inject(named("error"))
    val user: MutableLiveData<User> by inject(named("user"))
    val categories: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))
    val subCategories: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))
    val jobTypes: MutableLiveData<ArrayList<CategoryData>> by inject(named("categories"))
    val orders: MutableLiveData<ArrayList<Order>> by inject(named("orders"))
    val userOrders: MutableLiveData<ArrayList<Order>> by inject(named("orders"))
    val requests: MutableLiveData<ArrayList<OrderRequests>> by inject(named("orderRequests"))
    val userRequests: MutableLiveData<ArrayList<OrderRequests>> by inject(named("orderRequests"))

    private val compositeDisposable = CompositeDisposable()

    private val api = RetrofitClient
        .getRetrofit(Constants.BASE_URL, sharedManager, context)
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
            loge("Current token : " + sharedManager.token)

            getUser()
            getCategories()
        }
    }

    fun getUser() = compositeDisposable.add(
        api.getUser().observeAndSubscribe()
            .subscribe({
                sharedManager.user = it
                user.value = it
                MainActivity.client = it.is_constructor

                if (it.is_constructor) getUserOrders() else getUserRequests()

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

    fun register() {
        var avatar: MultipartBody.Part? = null
        RegisterModel.avatarFile?.let {
            avatar = createFileMultipart("avatarFile", File(it))
        }
        var documents: List<MultipartBody.Part>? = null
        RegisterModel.documents?.let {
            documents = it.map {
                createFileMultipart("documents", File(it))
            }
        }
        val partMap = hashMapOf<String, Any>()
        var emailToLogin: String
        RegisterModel.let {
            emailToLogin = it.email
            partMap["email"] = it.email
            partMap["name"] = it.name
            partMap["password"] = it.password
            partMap["phone"] = it.phone
            partMap["city"] = it.city
            partMap["contractor"] = if (it.client) 1 else 0
        }

        compositeDisposable.add(
            api.register(partMap, avatar, documents)
                .observeAndSubscribe()
                .subscribe({
                    val mails = ArrayList(sharedManager.mails)
                    mails.add(EmailData(emailToLogin, System.currentTimeMillis()))
                    mails.distinct()
                    sharedManager.mails = mails
                    login(emailToLogin, RegisterModel.password)
                }, {
                    parseError(it)
                })
        )
    }

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

    fun createOrder(partMap: Map<String, Any>, files: List<MultipartBody.Part>) =
        compositeDisposable.add(
            api.createOrder(partMap, files).observeAndSubscribe()
                .subscribe({
                    data.postValue(it)
                    getUserOrders()
                }, {
                    parseError(it)
                })
        )

    fun getAllOrder(jobType: Int? = null) = compositeDisposable.add(
        api.getAllOrder(jobType).observeAndSubscribe()
            .subscribe({
                orders.value = ArrayList(it.content)
            }, {
                parseError(it)
            })
    )

    fun getUserOrders() = compositeDisposable.add(
        api.getUserOrders().observeAndSubscribe()
            .subscribe({
                userOrders.value = ArrayList(it)
            }, {
                parseError(it)
            })
    )

    fun editOrder(id: Int) =
        compositeDisposable.add(
            api.editOrder(id).observeAndSubscribe()
                .subscribe({
                    data.postValue(it)
                    getUserOrders()
                }, {
                    parseError(it)
                })
        )

    fun deleteOrder(id: Int) =
        compositeDisposable.add(
            api.deleteOrder(id).observeAndSubscribe()
                .subscribe({
                    data.postValue(it)
                    getUserOrders()
                }, {
                    parseError(it)
                })
        )

    fun getOrderRequests(id: Int) =
        compositeDisposable.add(
            api.getOrderRequests(id).observeAndSubscribe()
                .subscribe({
                    requests.postValue(ArrayList(it))
                }, {
                    parseError(it)
                })
        )

    fun getUserRequests() =
        compositeDisposable.add(
            api.getUserRequests().observeAndSubscribe()
                .subscribe({
                    userRequests.postValue(ArrayList(it))
                }, {
                    parseError(it)
                })
        )

    fun requestForOrder(id: Int, msg: String) =
        compositeDisposable.add(
            api.requestForOrder(RequestForOrder(msg, id)).observeAndSubscribe()
                .subscribe({
                    data.postValue(it)
                    getOrderRequests(id)
                }, {
                    parseError(it)
                })
        )

    fun cancelOrderRequest(id: Int, msg: String) =
        compositeDisposable.add(
            api.cancelOrderRequest(id, CancelRequest(msg)).observeAndSubscribe()
                .subscribe({
                    data.postValue(it)
                    getOrderRequests(id)
                }, {
                    parseError(it)
                })
        )

    fun createFileMultipart(name: String, file: File) = MultipartBody.Part.createFormData(
        name, file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

fun <T> Single<T>.observeAndSubscribe() =
    subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
