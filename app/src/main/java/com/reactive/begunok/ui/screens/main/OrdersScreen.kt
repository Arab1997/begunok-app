package com.reactive.begunok.ui.screens.main

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.OrdersAdapter
import com.reactive.begunok.ui.screens.create_order.OrderInfoScreen
import com.reactive.begunok.utils.Constants
import kotlinx.android.synthetic.main.screen_orders.*

class OrdersScreen : BaseFragment(R.layout.screen_orders) {

    companion object {
        fun newInstance(jobType: Int): OrdersScreen {
            return OrdersScreen().apply {
                setData(jobType)
            }
        }

        fun newInstance(status: String): OrdersScreen {
            return OrdersScreen().apply {
                setData(status)
            }
        }

    }

    private fun setData(jobType: Int) {
        this.jobType = jobType
    }

    private fun setData(status: String) {
        this.status = status
    }

    private var jobType: Int? = null
    private var status: String? = null
    private lateinit var adapter: OrdersAdapter

    override fun initialize() {

        if (jobType != null || status != null) fetchData()

        title.text = when {
            jobType != null -> "Все заказы"
            status != null -> "Мои заказы"
            MainActivity.client -> "Мои заказы"
            else -> "Мои заявки"
        }

        adapter = OrdersAdapter {
            addFragment(OrderInfoScreen.newInstance(it))
        }

        recycler.adapter = adapter

        swipeLayout.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {
        when {
            jobType != null -> viewModel.getAllOrder(jobType)
            status != null -> viewModel.getUserOrders()
            MainActivity.client -> viewModel.getUserOrders()
        }
    }

    override fun observe() {
        viewModel.apply {

            userOrders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (MainActivity.client && jobType == null) {
                    when (status) {
                        null -> adapter.setData(it)
                        Constants.DONE -> {
                            val filtered = ArrayList(it.filter { it.status == Constants.DONE })
                            adapter.setData(filtered)
                        }
                        Constants.ALL_EXCEPT_DONE -> {
                            val filtered = ArrayList(it.filter { it.status != Constants.DONE })
                            adapter.setData(filtered)
                        }
                    }
                }
            })
            userRequests.observe(viewLifecycleOwner, Observer {
                if (!MainActivity.client && jobType == null) {
                    /*when (status) {
                        null -> adapter.setData(it)
                        Constants.DONE -> {
                            val filtered = ArrayList(it.filter { it.status == Constants.DONE })
                            adapter.setData(filtered)
                        }
                        Constants.ALL_EXCEPT_DONE -> {
                            val filtered = ArrayList(it.filter { it.status != Constants.DONE })
                            adapter.setData(filtered)
                        }
                    }*/
                }
            })

            orders.observe(viewLifecycleOwner, Observer {
                swipeLayout?.isRefreshing = false
                if (!MainActivity.client && jobType != null) {
                    adapter.setData(it)
                }
            })


        }
    }
}