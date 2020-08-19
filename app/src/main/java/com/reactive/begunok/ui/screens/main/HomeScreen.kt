package com.reactive.begunok.ui.screens.main

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.OrdersAdapter
import kotlinx.android.synthetic.main.screen_home.*

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private lateinit var adapter: OrdersAdapter

    override fun initialize() {

        title.text = if (MainActivity.customer) "Мои заказы" else "Мои заявки"

        adapter = OrdersAdapter {

        }
        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener {
            if (MainActivity.customer) viewModel.getAllOrder()
            else viewModel.getUserOrders()
        }
    }

    override fun observe() {
        viewModel.apply {

            userOrders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (!MainActivity.customer) adapter.setData(it)
            })

            orders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (MainActivity.customer) adapter.setData(it)
            })
        }
    }
}