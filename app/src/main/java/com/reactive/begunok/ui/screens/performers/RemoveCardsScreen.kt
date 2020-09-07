package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_add_cards_performer.*

class RemoveCardsScreen : BaseFragment(R.layout.screen_remove_cards_performer) {

    override fun initialize() {
        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    private fun initClicks() {
        save.setOnClickListener { inDevelopment(requireContext()) }
    }

    private fun initViews() {
        header.text = "Удалить платежную карту"
    }

}