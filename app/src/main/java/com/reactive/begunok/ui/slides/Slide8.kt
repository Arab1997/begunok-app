package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide8 : BaseSlide(R.layout.screen_choose_address) {

    override fun viewCreated() {

        title.text = "Создать заявку"
    }
}