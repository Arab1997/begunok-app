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

    private lateinit var fbListener: () -> Unit
    fun setFBListener(fbListener: () -> Unit) {
        this.fbListener = fbListener
    }

    override fun initialize() {

        login.setOnClickListener { addFragment(SignScreen.newInstance(true)) }

        register.setOnClickListener { addFragment(SignScreen.newInstance(false)) }

        google.setOnClickListener { googleListener.invoke() }

        fb.setOnClickListener { fbListener.invoke() }
    }

}