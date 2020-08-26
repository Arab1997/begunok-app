package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_alert.*

class AlertScreen : BaseFragment(R.layout.screen_alert) {

    override fun initialize() {

        back.setOnClickListener { finishFragment() }
        dim.setOnClickListener { finishFragment() }
    }
}