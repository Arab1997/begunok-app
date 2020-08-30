package com.reactive.begunok.network

import com.reactive.begunok.network.models.*
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

@JvmSuppressWildcards
interface ApiInterface {

    @POST("oauth/token")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String = "password"
    ): Single<Token>

    @Multipart
    @POST("api/v1/user")
    fun register(
        @PartMap partMap: Map<String, Any>,
        @Part avatar: MultipartBody.Part?,
        @Part documents: List<MultipartBody.Part>?
    ): Single<User>

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

    @Multipart
    @POST("api/v1/order")
    fun createOrder(
        @PartMap partMap: Map<String, Any>, @Part files: List<MultipartBody.Part>
    ): Single<Order>

    @PUT("api/v1/order/{id}")
    fun editOrder(@Path("id") id: Int): Single<Order>

    @DELETE("api/v1/order/{id}")
    fun deleteOrder(@Path("id") id: Int): Single<SuccessResp>

    @GET("api/v1/order")
    fun getAllOrder(@Query("jobType") jobType: Int? = null): Single<OrderResp>

    @GET("api/v1/order")
    fun getUserOrders(): Single<OrderResp>

    @POST("api/v1/order-request")
    fun requestForOrder(@Body request: RequestForOrder): Single<OrderRequests>

    @GET("api/v1/order-request/{id}")
    fun getOrderRequests(@Path("id") id: Int): Single<List<OrderRequests>>


}

data class ErrorResp(val message: String, val errors: Any? = null)

data class SuccessResp(val success: Boolean, val message: String)

data class Token(val access_token: String)

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val avatar: String?,
    val city: String?,
    val inProgressOrders: Int,
    val cancelledOrders: Int,
    val completedOrders: Int,
    val contractor: Boolean
)
