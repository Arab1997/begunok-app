package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_alert_delete_card_performer.*
import kotlinx.android.synthetic.main.screen_alert_phone_performer.cancel
import kotlinx.android.synthetic.main.screen_alert_delete_card_performer.delete

class AlertDeleteCardPerformerScreen : BaseFragment(R.layout.screen_alert_delete_card_performer) {

    override fun initialize() {

        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }
    private fun initClicks() {
        delete.setOnClickListener { addFragment(SavePhonePerformerScreen()) }
        cancel.setOnClickListener { finishFragment() }
    }

    private fun initViews() {
        header.text = "Изменить номер телефона"
    }

}