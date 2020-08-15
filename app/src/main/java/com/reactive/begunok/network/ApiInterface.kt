package com.reactive.begunok.network

import com.reactive.begunok.network.models.CategoryData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("authenticate/rest-auth/login")
    fun login(@Body body: LoginRequest): Single<Token>

    @POST("api/v1/user")
    fun register(@Body body: RegisterRequest): Single<TokenRegister>

    @POST("authenticate/rest-auth/logout")
    fun logout(): Single<Response<Void>>

    @GET("authenticate/customer_profiles")
    fun getProfile(): Single<User>

    @GET("api/v1/category")
    fun getCategories(): Single<List<CategoryData>>

    @GET("api/v1/sub-category")
    fun getSubCategories(@Query("categoryId") categoryId: Int): Single<List<CategoryData>>

    @GET("api/v1/job-type")
    fun getJobTypes(@Query("subCatId") categoryId: Int): Single<List<CategoryData>>

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
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val is_contractor: Boolean
)
