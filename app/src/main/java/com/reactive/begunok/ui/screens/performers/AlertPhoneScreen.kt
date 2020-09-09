package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_alert_email.*
import kotlinx.android.synthetic.main.screen_alert_phone.cancel
import kotlinx.android.synthetic.main.screen_alert_phone.save

class AlertPhoneScreen : BaseFragment(R.layout.screen_alert_phone) {

    override fun initialize() {

        back.setOnClickListener { finishFragment() }

        dim.setOnClickListener { finishFragment() }

        initClicks()
    }

    private fun initClicks() {
        save.setOnClickListener { addFragment(SavePhonePerformerScreen()) }

        cancel.setOnClickListener { finishFragment() }
    }


}