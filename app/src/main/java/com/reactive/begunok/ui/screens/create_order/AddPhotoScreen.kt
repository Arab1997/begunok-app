package com.reactive.begunok.ui.screens.create_order

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.adapters.AddImgAdapter
import com.reactive.begunok.utils.KeyValue
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_add_photo.*

class AddPhotoScreen : BaseFragment(R.layout.screen_add_photo) {

    private var images = arrayListOf<KeyValue>()
    private lateinit var adapter: AddImgAdapter

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        images = ArrayList((1..4).toList().map { KeyValue(it.toString(), "") })

        adapter = AddImgAdapter(false, {
            removePreviousCallbacks({ addImage(it) })
        }, {
            for (i in 0 until images.size) {
                if (images[i].key == it.key) images[i] = KeyValue(it.key, "")
            }
            adapter.setData(images)
        }).apply { setData(images) }

        recycler.adapter = adapter

        proceed.setOnClickListener {
            uploadPhotos()
            CreateOrderModel.photos = ArrayList(images.map { it.value }.filter { it.isNotEmpty() })
            addFragment(OrderInfoScreen())
        }
    }

    private fun addImage(it: KeyValue) {
        TedImagePicker.with(requireContext())
            .start { uri ->
                val path = mainActivity.getFilePath(uri)
                for (i in 0 until images.size) {
                    if (images[i].key == it.key) {
                        images[i] = KeyValue(it.key, path)
                    }
                }
                mainActivity.runOnUiThread { adapter.setData(images) }
            }
    }

    private fun uploadPhotos() {
        val imgList = images.filter { it.value.isNotEmpty() }
        // todo load images
    }
}