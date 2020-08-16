package com.reactive.begunok.ui.screens.create_order

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.screen_img.*

class ImgScreen : BaseFragment(R.layout.screen_img) {

    companion object {
        private var imgUrl: String? = null
        fun newInstance(imgUrl: String): ImgScreen {
            this.imgUrl = imgUrl
            return ImgScreen()
        }

    }

    override fun initialize() {

        dim.setOnClickListener { finishFragment() }

        imgUrl?.let { img.loadImage(it) }
    }
}