package com.reactive.begunok.ui.screens.create_order

import android.annotation.SuppressLint
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.extensions.toast
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_order_desc.*

class OrderDescScreen : BaseFragment(R.layout.screen_order_desc) {

    override fun initialize() {

        initViews()

        initClicks()
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        img1.setOnClickListener { addFragment(ImgScreen.newInstance(img1.drawable)) }
        img2.setOnClickListener { addFragment(ImgScreen.newInstance(img2.drawable)) }
        img3.setOnClickListener { addFragment(ImgScreen.newInstance(img3.drawable)) }

        create.setOnClickListener {
            it.blockClickable()
            toast(requireContext(), "Заказ создано")
            popInclusive()

            CreateOrderModel.let {
                it.email = email.text.toString()
                it.phone = phone.text.toString()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        title.text = "Создать заявку"

        CreateOrderModel.let {
            job.text = it.jobType!!.name
            desc.text = it.task
            date.text = it.date
            address.text = it.address
            price.text = "${it.price} грн"
        }

        email.setText(sharedManager.user.email)
        phone.setText(sharedManager.user.phone)

        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        check()
    }

    private fun check() {
        create.disable()

        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.invalid_email_adress)
            return
        }
        if (phone.rawText.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }

        create.enable()
    }

}