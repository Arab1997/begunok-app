package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_cancel_order.view.*

data class CancelData(val message: String, var checked: Boolean = false)

class CancelAdapter(private val listener: (pos: Int) -> Unit) :
    BaseAdapter<CancelData>(R.layout.item_cancel_order) {

    override fun bindViewHolder(holder: ViewHolder, data: CancelData) {
        holder.itemView.apply {
            setOnClickListener { listener.invoke(holder.adapterPosition) }
            name.text = data.message
            name.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0,
                0,
                if (data.checked) R.drawable.ic_checked else R.drawable.ic_not_checked,
                0
            )
        }
    }
}