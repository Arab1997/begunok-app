package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide12Fragment : BaseFragment(R.layout.slide12) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

    }
}