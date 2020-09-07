package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder

class CardsPagerAdapter (private val listener: (Any) -> Unit) : BaseAdapter<Any>(R.layout.item_add_cards) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        holder.itemView.setOnClickListener {
            listener.invoke(data)
        }
    }
    }
