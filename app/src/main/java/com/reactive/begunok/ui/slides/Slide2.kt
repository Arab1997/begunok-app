package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.content_toolbar.*

class Slide2 : BaseSlide(R.layout.screen_consolidation) {

    override fun viewCreated() {
        title.text = "Заявка"
    }

}