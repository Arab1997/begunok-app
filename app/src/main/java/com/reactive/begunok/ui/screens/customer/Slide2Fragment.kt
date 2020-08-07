package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide2Fragment : BaseFragment(R.layout.slide2) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Заявка"

    }
}