package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_finish_login.*

class FinishLoginScreen : BaseFragment(R.layout.screen_finish_login) {

    override fun initialize() {
        customer.setOnClickListener { inDevelopment(requireContext()) }
        executor.setOnClickListener { inDevelopment(requireContext()) }

    }

}