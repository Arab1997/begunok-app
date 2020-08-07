package com.reactive.begunok.ui.screens.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.Orders
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.item_slide13.view.*

class Slide13Fragment : BaseFragment(R.layout.slide13) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

    }


    class Adapter(private val data: ArrayList<Orders>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slide13, parent, false)
        )

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.apply {
                data[position].apply {
                    title.text = name.capitalize()
                    image.setImageResource(icon)
                    if (isFinished) {
                        finished.text = "выполнено"
                        finished.setTextColor(context.resources.getColor(R.color.yellow))
                    } else {
                        finished.text = "в работе"
                        finished.setTextColor(context.resources.getColor(R.color.accent))
                    }

                }
            }
        }

    }
}