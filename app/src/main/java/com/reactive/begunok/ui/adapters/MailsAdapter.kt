package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_cities.view.*

data class EmailData(val email: String, val lastDate: Long)
class MailsAdapter(private val listener: (EmailData) -> Unit) :
    BaseAdapter<EmailData>(R.layout.item_cities) {

    override fun bindViewHolder(holder: ViewHolder, data: EmailData) {

        holder.itemView.apply {
            setOnClickListener { listener.invoke(data) }
            name.text = data.email
        }
    }

}