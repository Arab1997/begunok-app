package com.reactive.begunok.ui.screens.auth.forgot

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import kotlinx.android.synthetic.main.screen_forgot.*

class ForgotScreen : BaseFragment(R.layout.screen_forgot) {

    private var request = false
    override fun initialize() {

        next.disable()

        next.setOnClickListener {
            it.blockClickable()
            sendRequest()
        }

        back.setOnClickListener { finishFragment() }

        phone.addTextChangedListener {
            if (phone.rawText.length == 10) {
                sendRequest()
                next.enable()
            } else {
                next.disable()
            }
        }
    }

    private fun sendRequest() {
        request = true
        showProgress(true)
//            viewModel.login(email.text.toString(), passw.text.toString())
    }

    override fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (request) {
                showProgress(false)
                addFragment(ForgotCodeScreen())
                request = false
            }
        })
    }

}