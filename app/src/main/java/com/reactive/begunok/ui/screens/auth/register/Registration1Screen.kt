package com.reactive.begunok.ui.screens.auth.register

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.screen_reg1.*


class Registration1Screen : BaseFragment(R.layout.screen_reg1) {

    private var request = false
    override fun initialize() {

        initViews()
    }

    private fun initViews() {
        next.disable()

        next.setOnClickListener {
            it.blockClickable()
            showProgress(true)
            viewModel.register(
                name.text.toString(),
                email.text.toString(),
                passw.text.toString(),
                "+38" + phone.rawText.toString()
            )
        }

        name.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        phone.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        passw.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        resPass.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
    }

    private fun check() {
        next.disable()

        if (name.text.toString().isEmpty()) {
            name.error = getString(R.string.field_is_empty)
            return
        }
        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.invalid_email_adress)
            return
        }
        if (phone.rawText.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }
        if (passw.text.toString().length < 6) {
            passw.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (resPass.text.toString().length < 6) {
            resPass.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (passw.text.toString() != resPass.text.toString()) {
            resPass.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        next.enable()
    }

    override fun observe() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (request) {
                showProgress(false)
                addFragment(Registration2Screen(), id = viewModel.authLayoutId)
                request = false
            }
        })
    }
}