package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_images.view.*

class ImgAdapter(private val listener: (String) -> Unit) :
    BaseAdapter<String>(R.layout.item_images) {

    override fun bindViewHolder(holder: ViewHolder, data: String) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
            img.loadImage(data)
        }
    }

}