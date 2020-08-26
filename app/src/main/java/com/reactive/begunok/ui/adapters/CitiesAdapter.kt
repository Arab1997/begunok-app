package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_cities.view.*

class CitiesAdapter(private val listener: (String) -> Unit) :
    BaseAdapter<String>(R.layout.item_cities) {

    override fun bindViewHolder(holder: ViewHolder, data: String) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
            name.text = data
        }
    }

}