package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.close
import kotlinx.android.synthetic.main.screen_alert_email.*

class AlertEmailScreen : BaseFragment(R.layout.screen_alert_email) {

    override fun initialize() {

        back.setOnClickListener { finishFragment() }

        dim.setOnClickListener { finishFragment() }

        initClicks()

    }

    private fun initClicks() {
        save.setOnClickListener { inDevelopment(requireContext()) } // todo

        cancel.setOnClickListener { finishFragment() }

    }
}