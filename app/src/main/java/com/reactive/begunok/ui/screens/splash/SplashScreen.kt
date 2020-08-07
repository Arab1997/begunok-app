package com.reactive.begunok.ui.screens.splash

import android.os.CountDownTimer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment

class SplashScreen : BaseFragment(R.layout.screen_splash) {

    private lateinit var listener: () -> Unit
    fun setListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun initialize() {

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                listener.invoke()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}