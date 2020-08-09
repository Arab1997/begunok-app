package com.reactive.begunok.ui.screens.customer

import android.os.Bundle
import android.view.View
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.CategoryRoundedAdapter
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_category_rounded.*

class CategoryRoundedScreen : BaseFragment(R.layout.screen_category_rounded) {
    private lateinit var adapter: CategoryRoundedAdapter

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
        close.setOnClickListener {
            finishFragment()
        }

        arguments?.let {
            show = it.getBoolean("show")
            header = it.getString("header", "")
            if (show) categoryEdt.visibility = View.VISIBLE
            if (header.isNotEmpty()) mainTitle.text = header
            title.text = "Создать заявку"
        }

//        adapter = CategoryRoundedAdapter()
//        recycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        show = false
        header = ""
    }


}