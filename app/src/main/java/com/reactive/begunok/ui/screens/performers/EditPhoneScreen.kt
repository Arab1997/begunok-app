package com.reactive.begunok.ui.screens.performers

import android.annotation.SuppressLint
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_phone.change
import kotlinx.android.synthetic.main.screen_edit_phone.phone

class EditPhoneScreen : BaseFragment(R.layout.screen_edit_phone) {

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
        change.setOnClickListener { addFragment(AlertPhoneScreen()) }
        phone.setOnClickListener { inDevelopment(requireContext()) } // todo
    }

    private fun initViews() {
        header.text = "Изменить номер телефона"
    }
}