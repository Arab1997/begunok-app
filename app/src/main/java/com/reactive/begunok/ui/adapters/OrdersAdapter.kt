package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.network.models.Order
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_my_orders.view.*

class OrdersAdapter(private val listener: (Order) -> Unit) :
    BaseAdapter<Order>(R.layout.item_my_orders) {

    override fun bindViewHolder(holder: ViewHolder, data: Order) {
        holder.itemView.apply {
            data.apply {

                setOnClickListener { listener.invoke(this) }
                arrangedDate.text = date
            }

        }
    }

}