package com.reactive.begunok.network.models

object CreateOrderModel {
    var category: CategoryData? = null
    var subCategory: CategoryData? = null
    var jobType: CategoryData? = null
    var task: String = ""
    var city: String = ""
    var address: String = ""
    var date: String = ""
    var price: Int = 0
    var photos: ArrayList<String> = arrayListOf()
    var email: String = ""
    var phone: String = ""

    fun clear() {
        category = null
        subCategory = null
        jobType = null
        task = ""
        city = ""
        address = ""
        date = ""
        price = 0
        photos = arrayListOf()
        email = ""
        phone = ""
    }
}