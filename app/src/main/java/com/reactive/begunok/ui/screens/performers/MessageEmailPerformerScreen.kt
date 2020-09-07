package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_message_email_performer.*

class MessageEmailPerformerScreen : BaseFragment(R.layout.screen_message_email_performer) {

    override fun initialize() {
        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    private fun initClicks() {
        save.setOnClickListener { inDevelopment(requireContext()) } // todo
        cancel.setOnClickListener { inDevelopment(requireContext()) } // todo
    }

    private fun initViews() {
        header.text = "Изменить эл. почту"
    }

}