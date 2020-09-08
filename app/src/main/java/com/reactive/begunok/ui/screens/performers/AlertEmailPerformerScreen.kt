package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_alert_email_performer.*

class AlertEmailPerformerScreen : BaseFragment(R.layout.screen_alert_email_performer) {

    override fun initialize() {
        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    private fun initClicks() {
        save.setOnClickListener {  addFragment(AlertEmailPerformerScreen()) }
        cancel.setOnClickListener { finishFragment() }

    }

    private fun initViews() {
        header.text = "Изменить эл. почту"
    }


    private fun open() {

        showProgress(true)
    }

}