package com.reactive.begunok.ui.adapters

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_category) {

    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        holder.itemView.apply {
            data.apply {
                container.setOnClickListener {
                    expandableLayout.toggle()
                    expIcon.setImageResource(
                        if (expandableLayout.isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24
                        else R.drawable.ic_baseline_chevron_right_24
                    )
                }
                recyclerChild.adapter = CategoryChildAdapter {}.apply {
                    setData(MainActivity.data)
                }

            }
        }
    }
}

