package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_create_request.*

class CreateRequestScreen : BaseFragment(R.layout.screen_create_request) {

    override fun initialize() {
        create.setOnClickListener { addFragment(CategoryScreen()) }
    }
}