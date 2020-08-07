package com.reactive.begunok.network.models

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

data class Category(
    val name: String,
    @DrawableRes val icon: Int,
    val child: ArrayList<ChildCategory> = arrayListOf()
)

data class FragmentData(val fragment: Fragment, val name: String)

data class ChildCategory(val name: String, var isChecked: Boolean = false)

data class Orders(val name: String, @DrawableRes val icon: Int, var isFinished: Boolean = false)
