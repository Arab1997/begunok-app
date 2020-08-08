package com.reactive.begunok.ui.screens.customer

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_description2.*

class Description2Screen : BaseFragment(R.layout.screen_description2) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Создать Заявка"
        initViews()

    }

    private fun initViews() {
        create.disable()

        create.setOnClickListener {
            it.blockClickable()
            //  addFragment()
        }
        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
    }

    private fun check() {
        create.disable()

        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        if (phone.text.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }
        create.enable()
    }

}