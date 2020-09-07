package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_save_phone_performer.*

class SavePhonePerformerScreen : BaseFragment(R.layout.screen_save_phone_performer) {

    override fun initialize() {

        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    private fun initClicks() {
        change.setOnClickListener { inDevelopment(requireContext()) } // todo

    }

    private fun initViews() {
        header.text = "Изменить номер телефона"
    }

}