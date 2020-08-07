package com.reactive.begunok.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("authenticate/rest-auth/login")
    fun login(@Body body: LoginRequest): Single<Token>

    @POST("authenticate/rest-auth/registration")
    fun register(@Body body: RegisterRequest): Single<TokenRegister>

    @POST("authenticate/rest-auth/logout")
    fun logout(): Single<Response<Void>>

    @GET("authenticate/customer_profiles")
    fun getProfile(): Single<User>
}

data class ErrorResp(val message: String, val errors: Any? = null)

data class MessageResp(val message: String)

data class SuccessResp(val success: Boolean)

data class Token(val token: String)
data class TokenRegister(val key: String)

data class LoginRequest(
    val username: String,
    val email: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password1: String,
    val password2: String
)

data class User(
    val user: Int,
    val phone: String,
    val address: String,
    val profile: String,
    val is_contractor: Boolean
)
