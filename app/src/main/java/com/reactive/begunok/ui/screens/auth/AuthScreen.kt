package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_auth.*

class AuthScreen : BaseFragment(R.layout.screen_auth) {

    override fun initialize() {
        reg.setOnClickListener { addFragment(SignFragment()) }
        enter.setOnClickListener { addFragment(SignFragment()) }

    }

}