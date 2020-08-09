package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.customer.CategoryRoundedScreen
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_choose_mode.*

class ChooseModeScreen : BaseFragment(R.layout.screen_choose_mode) {

    override fun initialize() {

        customer.setOnClickListener { addFragment(CategoryRoundedScreen()) }

        executor.setOnClickListener { inDevelopment(requireContext()) }
    }

}