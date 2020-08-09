package com.reactive.begunok.ui.screens.main

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.screen_profile.*

class ProfileScreen : BaseFragment(R.layout.screen_profile) {

    override fun initialize() {

        initViews()
    }

    private fun initViews() {
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
        card.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

    }

    private fun check() {
        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        if (phone.text.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }
        if (card.text.toString().length < 16) {
            card.error = getString(R.string.card_number_error)
            return
        }
    }


}