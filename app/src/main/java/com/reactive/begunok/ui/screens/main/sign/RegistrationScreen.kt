package com.reactive.begunok.ui.screens.main.sign

import android.util.Patterns
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextObservable
import com.reactive.begunok.utils.extensions.blockClickable
import kotlinx.android.synthetic.main.screen_registration.*


class RegistrationScreen : BaseFragment(R.layout.screen_registration) {

    override fun initialize() {

        email.addTextChangedListener(TextObservable(next) {})
        passw.addTextChangedListener(TextObservable(next) {})
        resPass.addTextChangedListener(TextObservable(next) {})

        next.setOnClickListener {

            if (passw.text.toString().length < 6) {
                passw.error = getString(R.string.minimum_password_length_6)
                return@setOnClickListener
            }
            if (passw.text.toString().length > 120) {
                passw.error = getString(R.string.maximum_password_length_120)
                return@setOnClickListener
            }
            if (passw.text.toString() != resPass.text.toString()) {
                resPass.error = getString(R.string.the_verified_password_is_not_valid)
                return@setOnClickListener
            }
            var emailAdress: String? = null
            emailAdress = email.text.toString()

            if (!(emailAdress.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAdress).matches())) {
                email.error = getString(R.string.the_verified_password_is_not_valid)
                return@setOnClickListener
            }



            it.blockClickable()
        }

    }


}