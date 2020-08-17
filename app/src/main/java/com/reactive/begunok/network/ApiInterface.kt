package com.reactive.begunok.network

import com.reactive.begunok.network.models.CategoryData
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @POST("oauth/token")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String = "password"
    ): Single<Token>

    @POST("api/v1/user")
    fun register(@Body body: RegisterRequest): Single<User>

    @PUT("api/v1/user/{id}")
    fun edit(@Body body: RegisterRequest, @Path("id") id: Int): Single<User>

    @PUT("api/v1/user/change-password")
    fun resetPassword(): Single<Token>

    @GET("api/v1/user/current")
    fun getUser(): Single<User>

    @GET("api/v1/category")
    fun getCategories(): Single<List<CategoryData>>

    @GET("api/v1/sub-category")
    fun getSubCategories(@Query("categoryId") categoryId: Int): Single<List<CategoryData>>

    @GET("api/v1/job-type")
    fun getJobTypes(@Query("subCatId") categoryId: Int): Single<List<CategoryData>>

}

data class ErrorResp(val message: String, val errors: Any? = null)

data class Token(val access_token: String)

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String,
    val phone: String,
    val contractor: Boolean = false
)

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val contractor: Boolean
)
