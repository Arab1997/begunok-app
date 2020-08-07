package com.reactive.begunok.ui.slides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.R
import com.reactive.begunok.network.models.Category
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.item_category_rounded.view.*
import kotlinx.android.synthetic.main.slide5.*

class Slide5 : BaseSlide(R.layout.slide5) {

    companion object {
        fun newInstance(show: Boolean, header: String): Slide5 {
            return Slide5().apply {
                arguments = Bundle().apply {
                    putBoolean("show", show)
                    putString("header", header)
                }
            }
        }
    }

    private var show: Boolean = false
    private var header: String = ""

    override fun viewCreated() {

        arguments?.let {
            show = it.getBoolean("show")
            header = it.getString("header", "")
            if (show) categoryEdt.visibility = View.VISIBLE

            if (header.isNotEmpty()) mainTitle.text = header
        }

        title.text = "Создать заявку"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        show = false
        header = ""
    }

    class Adapter(private val data: ArrayList<Category>, private val layout: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
                }
            }
        }

    }
}
