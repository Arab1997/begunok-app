package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.network.models.Order
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_my_orders.view.*

class NotificationsAdapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_notifications) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        holder.itemView.apply {
            data.apply {
                setOnClickListener { listener.invoke(this) }
            }
        }
    }
}