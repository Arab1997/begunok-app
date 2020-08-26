package com.reactive.begunok.ui.screens.auth.register

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.RegisterModel
import com.reactive.begunok.ui.adapters.AddImgAdapter
import com.reactive.begunok.utils.KeyValue
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.screen_reg2.*

class Registration2Screen : BaseFragment(R.layout.screen_reg2) {

    private var images = arrayListOf<KeyValue>()
    private lateinit var adapter: AddImgAdapter

    override fun initialize() {

        images = ArrayList((1..4).toList().map { KeyValue(it.toString(), "") })

        adapter = AddImgAdapter(false, { addImage(it) }, {
            for (i in 0 until images.size) {
                if (images[i].key == it.key) images[i] = KeyValue(it.key, "")
            }
            adapter.setData(images)
        }).apply { setData(images) }

        recycler.adapter = adapter

        next.setOnClickListener {
            RegisterModel.documents = images.filter { it.value.isNotEmpty() }.map { it.value }

            addFragment(Registration3Screen())
        }

        back.setOnClickListener { finishFragment() }
    }

    private fun addImage(it: KeyValue) {
        TedImagePicker.with(requireContext())
            .start { uri ->
                val path = mainActivity.getFilePath(uri)
                for (i in 0 until images.size) {
                    if (images[i].key == it.key) images[i] = KeyValue(it.key, path)
                }
                mainActivity.runOnUiThread { adapter.setData(images) }
            }
    }
}