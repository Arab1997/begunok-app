package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.network.models.OrderRequests
import com.reactive.begunok.utils.common.ViewHolder
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_order_request.view.*

class RequestsAdapter(
    private var client: Boolean,
    private val selectListener: (order: OrderRequests, select: Boolean?) -> Unit
) :
    BaseAdapter<OrderRequests>(R.layout.item_order_request) {

    override fun bindViewHolder(holder: ViewHolder, data: OrderRequests) {

        holder.itemView.apply {
            data.apply {

                user.avatar?.let { img.loadImage(it) }
                name.text = user.name
                desc.text = message

                if (client) {
                    setOnClickListener { selectListener.invoke(this, null) }
                    select.setOnClickListener { selectListener.invoke(this, true) }
                    delete.setOnClickListener { selectListener.invoke(this, false) }

                    // todo delete executor
//                    delete.visible()
//                    selectLayout.gone()
                }
            }
        }
    }

}