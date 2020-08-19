package com.reactive.begunok.network.models

data class CategoryData(
    val id: Int,
    val name: String,
    val icon: String,
    val iconUrl: String,
    val parentId: Int
)