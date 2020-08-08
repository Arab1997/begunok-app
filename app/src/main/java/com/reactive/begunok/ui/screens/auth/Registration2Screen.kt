package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_reg2.*

class Registration2Screen : BaseFragment(R.layout.screen_reg2) {

    override fun initialize() {
       next.setOnClickListener { addFragment(Registration3Screen()) }
        back.setOnClickListener { addFragment(Registration1Screen()) }
    }


}