package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.adapters.NotificationsAdapter
import kotlinx.android.synthetic.main.screen_orders.*

class NotificationsScreen : BaseFragment(R.layout.screen_orders) {

    private lateinit var adapter: NotificationsAdapter

    override fun initialize() {
        adapter = NotificationsAdapter {

        }
            .apply { setData(MainActivity.data) }
        recycler.adapter = adapter

        main.setBackgroundResource(R.drawable.back_notifications)
        title.text = "Уведомления"
    }
}