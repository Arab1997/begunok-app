package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*

class BalanceScreen : BaseFragment(R.layout.screen_balance) {

    override fun initialize() {

        close.setOnClickListener { finishFragment() }
    }
}