package com.reactive.begunok.ui.screens.others

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.content_toolbar.*

class CategoryScreen : BaseFragment(R.layout.screen_category) {

    private lateinit var adapter: CategoryAdapter

    override fun initialize() {
        close.setOnClickListener {
            finishFragment()
        }

//        adapter = CategoryAdapter()
//        recycler.adapter = adapter
    }
}


