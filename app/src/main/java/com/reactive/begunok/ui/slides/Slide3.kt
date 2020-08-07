package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide3 : BaseSlide(R.layout.slide3) {

    override fun viewCreated() {
        title.text = "Заявка"
    }

}