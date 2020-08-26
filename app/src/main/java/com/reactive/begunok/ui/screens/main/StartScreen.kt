package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.screens.create_order.CategoryScreen
import kotlinx.android.synthetic.main.screen_start.*

class StartScreen : BaseFragment(R.layout.screen_start) {

    override fun initialize() {

        if (MainActivity.client) {
            startContainer.setBackgroundResource(R.drawable.back_start_client)
            next.text = "создать заявку"
        } else {
            startContainer.setBackgroundResource(R.drawable.back_start_executor)
            next.text = "выбрать категорию"
        }

        next.setOnClickListener { addFragment(CategoryScreen()) }
    }
}