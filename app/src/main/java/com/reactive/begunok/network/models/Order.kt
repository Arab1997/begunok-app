package com.reactive.begunok.network.models

data class OrderResp(val content: List<Order>)

data class Order(
    val id: Int,
    val category: Int,
    val subCategory: Int,
    val jobType: Int,
    val task: String,
    val description: String,
    val city: String,
    val address: String,
    val date: String,
    val price: Int,
    val images: List<String>,
    val email: String,
    val phone: String
)