package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.screens.BottomNavScreen
import kotlinx.android.synthetic.main.screen_choose_mode.*

class ChooseModeScreen : BaseFragment(R.layout.screen_choose_mode) {

    override fun initialize() {

        customer.setOnClickListener { open(true) }
        customer1.setOnClickListener { open(true) }

        executor.setOnClickListener { open(false) }
        executor1.setOnClickListener { open(false) }
    }

    private fun open(customer: Boolean) {
        MainActivity.customer = customer
        replaceFragment(BottomNavScreen(), id = viewModel.parentLayoutId)
    }

}