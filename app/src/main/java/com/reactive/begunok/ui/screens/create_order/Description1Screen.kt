package com.reactive.begunok.ui.screens.create_order

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_description1.*

class Description1Screen : BaseFragment(R.layout.screen_description1) {

    override fun initialize() {
        close.setOnClickListener { finishFragment() }
        title.text = "Создать Заявка"
        img.setOnClickListener { addFragment(Description2Screen()) }

        initViews()

    }

    private fun initViews() {
        create.disable()

        create.setOnClickListener {
            it.blockClickable()
//            addFragment()
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