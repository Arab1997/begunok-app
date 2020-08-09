package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_auth.*

class AuthScreen : BaseFragment(R.layout.screen_auth) {

    override fun initialize() {

        login.setOnClickListener { addFragment(SignScreen()) }

        register.setOnClickListener { addFragment(SignScreen()) }

        google.setOnClickListener { inDevelopment(requireContext()) }

        fb.setOnClickListener { inDevelopment(requireContext()) }
    }

}