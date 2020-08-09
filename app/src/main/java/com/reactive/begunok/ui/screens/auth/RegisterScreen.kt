package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment

class RegisterScreen : BaseFragment(R.layout.screen_register) {

    override fun initialize() {
        replaceFragment(Registration1Screen(), id = viewModel.authLayoutId)
    }

}