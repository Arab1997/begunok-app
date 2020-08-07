package com.reactive.begunok.ui.screens.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.Category
import com.reactive.begunok.network.models.ChildCategory
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_category_child.view.*

class Slide1Fragment : BaseFragment(R.layout.slide1) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

    }


    class Adapter(private val data: ArrayList<Category>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.apply {
                data[position].apply {
                    title.text = name.capitalize()
                    image.setImageResource(icon)
                    container.setOnClickListener {
                        expandableLayout.toggle()

                        expIcon.setImageResource(
                            if (expandableLayout.isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24
                            else R.drawable.ic_baseline_chevron_right_24
                        )
                    }
                    recyclerChild.adapter = ChildAdapter(child)
                }
            }
        }

    }

    class ChildAdapter(private val data: ArrayList<ChildCategory>) :
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


}