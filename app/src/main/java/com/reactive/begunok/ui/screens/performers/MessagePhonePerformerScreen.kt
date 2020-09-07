package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_message_phone_performer.cancel
import kotlinx.android.synthetic.main.screen_message_phone_performer.save

class MessagePhonePerformerScreen : BaseFragment(R.layout.screen_message_phone_performer) {

    override fun initialize() {

        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }
    private fun initClicks() {
        save.setOnClickListener { addFragment(SavePhonePerformerScreen()) }
        cancel.setOnClickListener { finishFragment() }
    }

    private fun initViews() {
        header.text = "Изменить номер телефона"
    }

}