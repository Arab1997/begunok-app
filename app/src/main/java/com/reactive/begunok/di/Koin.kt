package com.reactive.begunok.di

import androidx.lifecycle.MutableLiveData
import com.reactive.begunok.base.BaseViewModel
import com.reactive.begunok.network.ErrorResp
import com.reactive.begunok.utils.preferences.PreferenceHelper
import com.reactive.begunok.utils.preferences.SharedManager
import com.google.gson.Gson
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {

    fun provideMutableLiveData() = MutableLiveData<Any>()

    viewModel { BaseViewModel(get(), get(), get()) }

    single { provideMutableLiveData() }
    single(named("sharedLive")) { provideMutableLiveData() }
    single(named("errorLive")) { MutableLiveData<ErrorResp>() }
}

val networkModule = module {

    fun provideGson() = Gson()

    single { provideGson() }
}

val sharedPrefModule = module {

    factory { PreferenceHelper.customPrefs(get(), "Qweep") }

    factory { SharedManager(get(), get(), get()) }
}