package com.reactive.begunok.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.network.models.Category
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_category_rounded.view.*

class CategoryRoundedAdapter(private val data: ArrayList<Category>, private val layout: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            data[position].apply {
                title?.text = name.capitalize()
                image?.setImageResource(icon)
                title.setOnClickListener {
                    if (onItemClickListener != null) onItemClickListener!!.onClick(
                        position
                    )
                }

            }
        }
    }

    interface OnItemClickListener {
        fun onClick(
            position: Int
        )
    }

}
