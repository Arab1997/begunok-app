package com.reactive.begunok.ui.screens.auth.forgot

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.base.parentLayoutId
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import kotlinx.android.synthetic.main.screen_forgot_password.*

class ForgotPasswordScreen : BaseFragment(R.layout.screen_forgot_password) {

    private var request = false
    override fun initialize() {

        initViews()

        next.setOnClickListener {
            it.blockClickable()
            request = true
            showProgress(true)
//            viewModel.login(email.text.toString(), passw.text.toString())
        }

    }

    private fun initViews() {
        next.disable()

        passw.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        passwConfirm.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
    }

    private fun check() {
        next.disable()

        if (passw.text.toString().length < 6) {
            passw.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (passwConfirm.text.toString().length < 6) {
            passwConfirm.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (passw.text.toString() != passwConfirm.text.toString()) {
            passwConfirm.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        next.enable()
    }

    override fun observe() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (request) {
                showProgress(false)
                MainActivity.client = it.contractor
                replaceFragment(BottomNavScreen(), id = parentLayoutId())
                request = false
            }
        })
    }

}