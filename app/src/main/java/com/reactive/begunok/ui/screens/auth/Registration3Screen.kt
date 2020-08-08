package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_reg3.*

class Registration3Screen : BaseFragment(R.layout.screen_reg3) {

    override fun initialize() {
       next.setOnClickListener { addFragment(ChooseModeScreen()) }
        back.setOnClickListener { addFragment(Registration2Screen()) }

    }


}