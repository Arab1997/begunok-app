package com.reactive.begunok.network

import android.content.Context
import com.reactive.begunok.BuildConfig
import com.reactive.begunok.utils.network.UnsafeOkHttpClient
import com.reactive.begunok.utils.preferences.SharedManager
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitClient {

    fun getRetrofit(
        baseUrl: String,
        sharedManager: SharedManager,
        context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient(sharedManager, context))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(sharedManager: SharedManager, context: Context): OkHttpClient {
        val builder = UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder()
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("TTT").i(message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
        builder.addInterceptor(BasicAuthInterceptor("android", "Yy5jruBmmWTjDDj6"))
        builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
            if (sharedManager.token.isNotEmpty()) request.addHeader(
                "Authorization",
                "Bearer ${sharedManager.token}"
            )
            chain.proceed(request.build())
        })
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        return builder.build()
    }

    class BasicAuthInterceptor(username: String, password: String) : Interceptor {
        private var credentials: String = Credentials.basic(username, password)

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            request = request.newBuilder().header("Authorization", credentials).build()
            return chain.proceed(request)
        }
    }
}
