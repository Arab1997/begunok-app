package com.reactive.begunok.ui.screens.performers

import android.annotation.SuppressLint
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_email_performer.*
import kotlinx.android.synthetic.main.screen_edit_phone_performer.*
import kotlinx.android.synthetic.main.screen_edit_phone_performer.change
import kotlinx.android.synthetic.main.screen_edit_phone_performer.phone

class EditPhonePerformerScreen : BaseFragment(R.layout.screen_edit_phone_performer) {

    override fun initialize() {
        setData()
        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        sharedManager.user?.let {
            phone.setText(it.phone)
        }
    }

    private fun initClicks() {
        change.setOnClickListener { addFragment(AlertPhonePerformerScreen()) }
        phone.setOnClickListener { inDevelopment(requireContext()) } // todo
    }

    private fun initViews() {
        header.text = "Изменить номер телефона"
    }
}