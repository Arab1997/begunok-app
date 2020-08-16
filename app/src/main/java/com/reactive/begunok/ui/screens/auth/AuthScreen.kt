package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_auth.*

class AuthScreen : BaseFragment(R.layout.screen_auth) {

    private lateinit var googleListener: () -> Unit
    fun setGoogleListener(googleListener: () -> Unit) {
        this.googleListener = googleListener
    }

    override fun initialize() {

        login.setOnClickListener { addFragment(SignScreen()) }

        register.setOnClickListener { addFragment(SignScreen()) }

        google.setOnClickListener { googleListener.invoke() }

        fb.setOnClickListener { inDevelopment(requireContext()) }
    }

}