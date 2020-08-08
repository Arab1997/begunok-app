package com.reactive.begunok.ui.screens.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.Category
import com.reactive.begunok.ui.adapters.CategoryRoundedAdapter
import com.reactive.begunok.ui.slides.Slide5
import com.reactive.begunok.utils.common.ViewHolder
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.item_category_rounded.view.*
import kotlinx.android.synthetic.main.screen_category_rounded.*

class CategoryRoundedScreen : BaseFragment(R.layout.screen_category_rounded) {


    private lateinit var adapter:CategoryRoundedAdapter

    companion object {
        fun newInstance(show: Boolean, header: String): CategoryRoundedScreen {
            return CategoryRoundedScreen().apply {
                arguments = Bundle().apply {
                    putBoolean("show", show)
                    putString("header", header)
                }
            }
        }
    }

    private var show: Boolean = false
    private var header: String = ""

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        arguments?.let {
            show = it.getBoolean("show")
            header = it.getString("header", "")
            if (show) categoryEdt.visibility = View.VISIBLE

            if (header.isNotEmpty()) mainTitle.text = header

            title.text = "Создать заявку"

            //adapter=CategoryRoundedAdapter()
            recycler.adapter=adapter

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        show = false
        header = ""
    }

}