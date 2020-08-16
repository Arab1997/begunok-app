package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.KeyValue
import com.reactive.begunok.utils.common.ViewHolder
import com.reactive.begunok.utils.extensions.loadFromResources
import com.reactive.begunok.utils.extensions.loadImage
import com.reactive.begunok.utils.extensions.showGone
import kotlinx.android.synthetic.main.item_add_photo.view.*

class AddImgAdapter(
    private val isRegistration: Boolean,
    private val listener: (KeyValue) -> Unit,
    private val delete: (KeyValue) -> Unit
) :
    BaseAdapter<KeyValue>(R.layout.item_add_photo) {

    override fun bindViewHolder(holder: ViewHolder, data: KeyValue) {

        holder.itemView.apply {
            if (data.value.isNotEmpty()) img.loadImage(data.value)
            else img.loadFromResources(if (isRegistration) R.drawable.shape_register else R.drawable.shape)

            delImg.showGone(data.value.isNotEmpty())
            img.setOnClickListener { listener.invoke(data) }
            delImg.setOnClickListener { delete.invoke(data) }
        }
    }

}