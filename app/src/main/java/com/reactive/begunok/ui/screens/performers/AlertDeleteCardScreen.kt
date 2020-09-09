package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.content_header.close
import kotlinx.android.synthetic.main.screen_alert_phone.cancel
import kotlinx.android.synthetic.main.screen_alert_delete_card.delete
import kotlinx.android.synthetic.main.screen_alert_email.*

class AlertDeleteCardScreen : BaseFragment(R.layout.screen_alert_delete_card) {

    override fun initialize() {
        initClicks()

        back.setOnClickListener { finishFragment() }

        dim.setOnClickListener { finishFragment() }

        initClicks()
    }
    private fun initClicks() {
        delete.setOnClickListener { inDevelopment(requireContext()) } // todo
        cancel.setOnClickListener { finishFragment() }
    }

}