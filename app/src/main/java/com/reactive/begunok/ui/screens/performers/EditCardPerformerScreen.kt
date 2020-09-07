package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.CardsPagerAdapter
import com.reactive.begunok.ui.screens.main.ProfileScreen
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_card_performer.*
import kotlinx.android.synthetic.main.screen_edit_card_performer.recycler

class EditCardPerformerScreen : BaseFragment(R.layout.screen_edit_card_performer) {
    private lateinit var adapter: CardsPagerAdapter

    override fun initialize() {
        initClicks()
        initViews()
        close.setOnClickListener { finishFragment() }

        adapter = CardsPagerAdapter {
        }
            .apply { setData(ProfileScreen.data) }
        recycler.adapter = adapter

    }

    private fun initClicks() {
        add.setOnClickListener { addFragment(AddCardsScreen()) }
        remove.setOnClickListener { addFragment(RemoveCardsScreen()) }
    }
    private fun initViews() {
        header.text = "Мои платежные карты"

    }
}