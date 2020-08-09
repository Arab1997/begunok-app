package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_category_rounded.view.*

class CategoryRoundedAdapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_category_rounded) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        holder.itemView.apply {
            data.apply {
                title.setOnClickListener { listener.invoke(this) }
            }
        }
    }
}
