package com.reactive.begunok.network.models

object RegisterModel {
    var email: String = ""
    var name: String = ""
    var password: String = ""
    var phone: String = ""
    var city: String = ""
    var avatarFile: String? = null
    var documents: List<String>? = null
    var client: Boolean = false

    fun clear() {
        email = ""
        name = ""
        password = ""
        phone = ""
        city = ""
        avatarFile = null
        documents = null
        client = false
    }
}