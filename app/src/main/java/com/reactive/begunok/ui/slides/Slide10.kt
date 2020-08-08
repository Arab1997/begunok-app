package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide10 : BaseSlide(R.layout.screen_description1) {

    override fun viewCreated() {

        title.text = "Создать заявку"
    }
}