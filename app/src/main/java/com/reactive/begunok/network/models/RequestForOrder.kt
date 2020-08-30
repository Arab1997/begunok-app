package com.reactive.begunok.network.models

import com.reactive.begunok.network.User

data class OrderRequests(
    val id: Int,
    val message: String,
    val orderId: Int,
    val user: User
)

data class RequestForOrder(
    val message: String,
    val orderId: Int
)