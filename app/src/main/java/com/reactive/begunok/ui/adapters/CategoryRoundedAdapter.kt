package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.network.models.CategoryData
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_category_rounded.view.*

class CategoryRoundedAdapter(private val listener: (CategoryData) -> Unit) :
    BaseAdapter<CategoryData>(R.layout.item_category_rounded) {

    override fun bindViewHolder(holder: ViewHolder, data: CategoryData) {
        holder.itemView.apply {
            data.apply {

                title.text = name
//                image.loadImage(icon)

                setOnClickListener { listener.invoke(this) }
            }
        }
    }
}
