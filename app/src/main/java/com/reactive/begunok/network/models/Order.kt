package com.reactive.begunok.network.models

import android.annotation.SuppressLint
import com.reactive.begunok.network.User
import java.text.SimpleDateFormat
import java.util.*

data class OrderResp(val content: List<Order>)

data class Order(
    val id: Int,
    val category: CategoryData,
    val subCategory: CategoryData,
    val jobType: CategoryData,
    val task: String,
    val description: String,
    val city: String,
    val address: String,
    val date: String,
    val price: Int,
    val images: List<String>,
    val email: String,
    val phone: String,
    val createdDate: Long,
    val updatedDate: Long,
    val user: User,
    val status: String
)

@SuppressLint("SimpleDateFormat")
val sdf = SimpleDateFormat("dd.MM.yy")
fun Long.parseDate() {
    sdf.format(Date(this * 1000L))
}