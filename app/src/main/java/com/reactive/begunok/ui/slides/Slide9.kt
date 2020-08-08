package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide9 : BaseSlide(R.layout.screen_add_photo) {

    override fun viewCreated() {

        title.text = "Создать заявку"
    }
}