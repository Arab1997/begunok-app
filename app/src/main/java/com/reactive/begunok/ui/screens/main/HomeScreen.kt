package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.MyOrdersAdapter
import kotlinx.android.synthetic.main.screen_home.*

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private lateinit var adapter: MyOrdersAdapter

    override fun initialize() {
        adapter = MyOrdersAdapter().apply {
            setData(MainActivity.data)
        }
        recycler.adapter = adapter
    }
}