package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.slide4.*


class Slide4a : BaseSlide(R.layout.slide4) {

    override fun viewCreated() {
        image.setImageResource(R.drawable.dislike)
    }

}