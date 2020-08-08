package com.reactive.begunok.ui.slides

import com.reactive.begunok.R
import kotlinx.android.synthetic.main.screen_profile.*


class Slide4a : BaseSlide(R.layout.screen_profile) {

    override fun viewCreated() {
        image.setImageResource(R.drawable.dislike)
    }

}