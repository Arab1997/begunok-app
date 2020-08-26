package com.reactive.begunok.ui.screens.create_order

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.CategoryRoundedAdapter
import com.reactive.begunok.ui.screens.main.OrdersScreen
import com.reactive.begunok.utils.extensions.visible
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_category_rounded.*

class JobTypeScreen : BaseFragment(R.layout.screen_category_rounded) {

    companion object {
        private var header: String = ""
        fun newInstance(header: String): JobTypeScreen {
            this.header = header
            return JobTypeScreen()
        }
    }

    private lateinit var adapter: CategoryRoundedAdapter
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        categoryEdt.setText(header)
        categoryEdt.visible()

        mainTitle.text = "Выбрать тип работ"

        adapter = CategoryRoundedAdapter {
            CreateOrderModel.jobType = it
            addFragment(
                if (MainActivity.client) AddAddressScreen()
                else OrdersScreen.newInstance(it.id)
            )
        }
        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener {
            viewModel.getJobTypes(CreateOrderModel.subCategory!!.id)
        }
    }

    override fun observe() {
        viewModel.getJobTypes(CreateOrderModel.subCategory!!.id)
        viewModel.jobTypes.observe(viewLifecycleOwner, Observer {
            swipeLayout?.isRefreshing = false
            adapter.setData(it)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            swipeLayout?.isRefreshing = false
        })
    }
}