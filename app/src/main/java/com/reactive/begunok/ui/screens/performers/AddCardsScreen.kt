package com.reactive.begunok.ui.screens.performers

import android.util.Log
import android.view.View
import com.fevziomurtekin.payview.data.PayModel
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_add_cards_performer.*


class AddCardsScreen : BaseFragment(R.layout.screen_add_cards_performer) {

    override fun initialize() {
        initClicks()
        initViews()

    }

    private fun initClicks() {
        save.setOnClickListener { inDevelopment(requireContext()) }
        close.setOnClickListener { finishFragment() }
    }

    private fun initViews() {
        header.text = "Добавить платежную карту"


       // val creditCardView = CreditCardView(context)

        val name = "HARISH SRIDHARAN"
        val cvv = "522"
        val expiry = "01/17"
        val cardNumber = "38056789000000000"


        payview.setOnDataChangedListener(object : com.fevziomurtekin.payview.Payview.OnChangelistener{
            override fun onChangelistener(payModel: PayModel?, isFillAllComponents: Boolean) {
                Log.d("PayView", "data : ${payModel?.cardOwnerName} \n " +
                        "is Fill all form component : $isFillAllComponents")

            }

        })

        payview.setPayOnclickListener(View.OnClickListener {
            Log.d("PayView "," clicked. iss Fill all form Component : ${payview.isFillAllComponents}")

        })


    }

}