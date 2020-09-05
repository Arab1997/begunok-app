package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_balance.*

class BalanceScreen : BaseFragment(R.layout.screen_balance) {

    override fun initialize() {
        add.setOnClickListener { inDevelopment(requireContext()) }
    }
}