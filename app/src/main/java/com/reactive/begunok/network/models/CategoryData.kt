package com.reactive.begunok.network.models

data class CategoryData(
    val id: Int,
    val name: String,
    val iconUrl: String?,
    val parentId: CategoryData?
)