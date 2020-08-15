package com.reactive.begunok.ui.screens.create_order

import android.widget.ImageView
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.utils.KeyValue
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.loadImage
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_add_photo.*

class AddPhotoScreen : BaseFragment(R.layout.screen_add_photo) {

    private var images = arrayListOf<KeyValue>()
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        img1.setOnClickListener { img1.addImage() }
        img2.setOnClickListener { img2.addImage() }
        img3.setOnClickListener { img3.addImage() }
        img4.setOnClickListener { img4.addImage() }

        proceed.setOnClickListener {
            CreateOrderModel.photos = ArrayList(images.map { it.value })
            addFragment(OrderDescScreen())
        }
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