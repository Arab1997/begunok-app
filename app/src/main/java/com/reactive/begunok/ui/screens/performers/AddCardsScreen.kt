package com.reactive.begunok.ui.screens.performers

import android.text.InputType
import com.braintreepayments.cardform.view.CardForm
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


    }

}