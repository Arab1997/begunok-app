package com.reactive.begunok.di

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.reactive.begunok.base.BaseViewModel
import com.reactive.begunok.network.ErrorResp
import com.reactive.begunok.network.User
import com.reactive.begunok.network.models.CategoryData
import com.reactive.begunok.network.models.Order
import com.reactive.begunok.utils.preferences.PreferenceHelper
import com.reactive.begunok.utils.preferences.SharedManager
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {

    fun provideMutableLiveData() = MutableLiveData<Any>()

    viewModel { BaseViewModel(get(), get(), get()) }

    single { provideMutableLiveData() }
    single(named("shared")) { provideMutableLiveData() }
    single(named("error")) { MutableLiveData<ErrorResp>() }
    single(named("user")) { MutableLiveData<User>() }

    factory(named("categories")) { MutableLiveData<ArrayList<CategoryData>>() }
    factory(named("orders")) { MutableLiveData<ArrayList<Order>>() }
}

val networkModule = module {

    fun provideGson() = Gson()

    single { provideGson() }
}

val sharedPrefModule = module {

    factory { PreferenceHelper.customPrefs(get(), "Qweep") }

    factory { SharedManager(get(), get(), get()) }
}
