package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.extensions.inDevelopment
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.screen_login.*

class LoginScreen : BaseFragment(R.layout.screen_login) {

    override fun initialize() {
        initViews()
    }

    private fun initViews() {
        next.disable()

        next.setOnClickListener {
            it.blockClickable()
            addFragment(ChooseModeScreen())

        }

        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        passw.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        failed.setOnClickListener { inDevelopment(requireContext()) }
    }

    private fun check() {
        next.disable()

        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        if (passw.text.toString().length < 6) {
            passw.error = getString(R.string.minimum_password_length_6)
            return
        }
        next.enable()
    }

}