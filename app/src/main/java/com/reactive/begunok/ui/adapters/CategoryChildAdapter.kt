package com.reactive.begunok.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.R
import com.reactive.begunok.network.models.ChildCategory
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_category_child.view.*

class CategoryChildAdapter(private val data: ArrayList<ChildCategory>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_category_child, parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            data[position].apply {
                childTitle.text = name.capitalize()
                childImage.setImageResource(
                    if (isChecked) R.drawable.ic_checked
                    else R.drawable.ic_not_checked
                )
                childContainer.setOnClickListener {
                    isChecked = !isChecked
                    childImage.setImageResource(
                        if (isChecked) R.drawable.ic_checked
                        else R.drawable.ic_not_checked
                    )
                }
            }
        }
    }
}
