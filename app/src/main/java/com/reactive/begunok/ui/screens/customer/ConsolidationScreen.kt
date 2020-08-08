package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class ConsolidationScreen : BaseFragment(R.layout.screen_consolidation) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Заявка"

    }
}