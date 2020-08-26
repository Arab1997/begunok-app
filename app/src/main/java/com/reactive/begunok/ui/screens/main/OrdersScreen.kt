package com.reactive.begunok.ui.screens.main

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.OrdersAdapter
import com.reactive.begunok.ui.screens.create_order.OrderInfoScreen
import kotlinx.android.synthetic.main.screen_orders.*

class OrdersScreen : BaseFragment(R.layout.screen_orders) {

    companion object {
        fun newInstance(jobType: Int): OrdersScreen {
            return OrdersScreen().apply {
                setData(jobType)
            }
        }
    }

    private fun setData(jobType: Int) {
        this.jobType = jobType
        viewModel.getAllOrder()
    }

    private var jobType: Int? = null
    private lateinit var adapter: OrdersAdapter
    override fun initialize() {

        title.text = when {
            jobType != null -> "Все заказы"
            MainActivity.client -> "Мои заказы"
            else -> "Мои заявки"
        }

        adapter = OrdersAdapter {
            addFragment(OrderInfoScreen.newInstance(it))
        }

        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener {
            when {
                jobType != null -> viewModel.getAllOrder(jobType)
                MainActivity.client -> viewModel.getUserOrders()
                else -> viewModel.getAllOrder(null)
            }
        }
    }

    override fun observe() {
        viewModel.apply {

            userOrders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (MainActivity.client && jobType == null) adapter.setData(it)
            })

            orders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (!MainActivity.client || jobType != null) adapter.setData(it)
            })
        }
    }
}