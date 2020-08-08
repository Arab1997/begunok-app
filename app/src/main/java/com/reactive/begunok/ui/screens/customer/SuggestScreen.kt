package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class SuggestScreen : BaseFragment(R.layout.screen_suggest) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Заявка"
    }
}