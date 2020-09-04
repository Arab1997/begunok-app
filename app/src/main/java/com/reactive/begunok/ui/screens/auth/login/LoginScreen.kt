package com.reactive.begunok.ui.screens.auth.login

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.base.parentLayoutId
import com.reactive.begunok.ui.adapters.EmailData
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.ui.screens.auth.VariantsScreen
import com.reactive.begunok.ui.screens.auth.forgot.ForgotScreen
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.extensions.inDevelopment
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.screen_login.*

class LoginScreen : BaseFragment(R.layout.screen_login) {

    private var request = false
    override fun initialize() {

        initViews()

        val mails = sharedManager.mails
        if (mails.isNotEmpty()) {
            email.setOnClickListener {
                hideKeyboard()
                addFragment(VariantsScreen())
            }
        }

        next.setOnClickListener {
            it.blockClickable()
            request = true
            showProgress(true)
            viewModel.login(email.text.toString(), passw.text.toString())
        }

        forgot.setOnClickListener { addFragment(ForgotScreen()) }
    }

    private fun initViews() {
        next.disable()

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

        forgot.setOnClickListener { inDevelopment(requireContext()) } // todo
    }

    private fun check() {
        next.disable()

        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.invalid_email_adress)
            return
        }
        if (passw.text.toString().length < 6) {
            passw.error = getString(R.string.minimum_password_length_6)
            return
        }
        next.enable()
    }

    override fun observe() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (request) {
                showProgress(false)
                replaceFragment(BottomNavScreen(), id = parentLayoutId())
                request = false
                val mails = ArrayList(sharedManager.mails)
                mails.add(EmailData(email.text.toString(), System.currentTimeMillis()))
                mails.distinct()
                sharedManager.mails = mails
            }
        })
        viewModel.shared.observe(viewLifecycleOwner, Observer {
            if (it is EmailData) {
                email.setText(it.email)
            }
        })
    }

}