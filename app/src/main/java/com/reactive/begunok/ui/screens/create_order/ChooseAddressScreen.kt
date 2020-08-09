package com.reactive.begunok.ui.screens.create_order

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_choose_address.*

class ChooseAddressScreen : BaseFragment(R.layout.screen_choose_address) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Создать Заявка"
        proceed.setOnClickListener { addFragment(AddPhotoScreen()) }


    }
}