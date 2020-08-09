package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder

class CategoryChildAdapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_category_child) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        holder.itemView.apply {
            data.apply {

            }
        }
    }
}
