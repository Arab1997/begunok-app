package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseAdapter
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.MyOrdersAdapter
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_my_orders.*

class MyOrdersScreen : BaseFragment(R.layout.screen_my_orders) {

    private lateinit var adapter: MyOrdersAdapter

    override fun initialize() {
        close.setOnClickListener { finishFragment() }

//        adapter=MyOrdersAdapter()
        recycler.adapter=adapter

    }


}