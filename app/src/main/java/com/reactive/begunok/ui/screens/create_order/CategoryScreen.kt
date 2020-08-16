package com.reactive.begunok.ui.screens.create_order

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.adapters.CategoryRoundedAdapter
import com.reactive.begunok.utils.extensions.gone
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_category_rounded.*

class CategoryScreen : BaseFragment(R.layout.screen_category_rounded) {

    private lateinit var adapter: CategoryRoundedAdapter

    override fun initialize() {

        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        categoryEdt.gone()

        adapter = CategoryRoundedAdapter {
            CreateOrderModel.category = it
            addFragment(SubCategoryScreen.newInstance(it.name))
        }
        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener { viewModel.getCategories() }
    }

    override fun observe() {
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            swipeLayout?.isRefreshing = false
            adapter.setData(it)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            swipeLayout?.isRefreshing = false
        })
    }
}