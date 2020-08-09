package com.reactive.begunok.ui.screens.create_order

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_add_photo.*

class AddPhotoScreen : BaseFragment(R.layout.screen_add_photo) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Создать Заявка"
        proceed.setOnClickListener { addFragment(Description1Screen()) }

    }
}