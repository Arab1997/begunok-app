package com.reactive.begunok.ui.screens.create_order

import android.graphics.drawable.Drawable
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.loadImageDrawable
import kotlinx.android.synthetic.main.screen_img.*

class ImgScreen : BaseFragment(R.layout.screen_img) {

    companion object {
        private var imgUrl: Drawable? = null
        fun newInstance(imgUrl: Drawable): ImgScreen {
            this.imgUrl = imgUrl
            return ImgScreen()
        }

    }

    override fun initialize() {

        dim.setOnClickListener { finishFragment() }

        imgUrl?.let { img.loadImageDrawable(it) }
    }
}