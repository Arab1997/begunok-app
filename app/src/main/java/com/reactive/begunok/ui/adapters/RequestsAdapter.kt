package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder

class RequestsAdapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_order_request) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
        }
    }

}