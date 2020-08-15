package com.reactive.begunok.ui.screens.create_order

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.adapters.CategoryRoundedAdapter
import com.reactive.begunok.utils.extensions.visible
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_category_rounded.*

class SubCategoryScreen : BaseFragment(R.layout.screen_category_rounded) {

    companion object {
        private var header: String = ""
        fun newInstance(header: String): SubCategoryScreen {
            this.header = header
            return SubCategoryScreen()
        }
    }

    private lateinit var adapter: CategoryRoundedAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        categoryEdt.setText(header)
        categoryEdt.visible()

        mainTitle.text = "Выбрать подкатегорию"

        adapter = CategoryRoundedAdapter {
            CreateOrderModel.subCategory = it
            addFragment(JobTypeScreen.newInstance("$header: ${it.name}"))
        }
        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener {
            showProgress(true)
            viewModel.getSubCategories(CreateOrderModel.category!!.id)
        }
    }

    override fun observe() {
        viewModel.getSubCategories(CreateOrderModel.category!!.id)
        viewModel.subCategories.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            adapter.setData(it)
        })
    }

}