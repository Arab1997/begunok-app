package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_add_cards.view.*
import kotlinx.android.synthetic.main.item_cities.view.*
import kotlinx.android.synthetic.main.item_images.view.*
import kotlinx.android.synthetic.main.item_images.view.img
import kotlinx.android.synthetic.main.item_my_orders.view.*

class CardsAdapter(private val listener: (String) -> Unit) :
    BaseAdapter<String>(R.layout.item_add_cards) {

    override fun bindViewHolder(holder: ViewHolder, data: String) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
            cards.loadImage(data)

        }
    }

}