package com.reactive.begunok.ui.screens.auth

import android.widget.ImageView
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.KeyValue
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.loadImage
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.screen_reg2.*

class Registration2Screen : BaseFragment(R.layout.screen_reg2) {

    private var images = arrayListOf<KeyValue>()

    override fun initialize() {

        next.setOnClickListener { addFragment(Registration3Screen(), id = viewModel.authLayoutId) }

        back.setOnClickListener { finishFragment() }

        img1.setOnClickListener { img1.addImage() }
        img2.setOnClickListener { img2.addImage() }
        img3.setOnClickListener { img3.addImage() }
        img4.setOnClickListener { img4.addImage() }
    }

    private fun ImageView.addImage() {
        this.blockClickable()
        TedImagePicker.with(requireContext())
            .start { uri ->
                val path = uri.path!!
                images.add(KeyValue(this.id.toString(), path))
                this.loadImage(path)
            }
    }

    private fun filterImages() {
        images = ArrayList(images.reversed().distinctBy { it.key }.reversed())
    }
}