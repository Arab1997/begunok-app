package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.common.FBAuthManager
import com.reactive.begunok.utils.common.GoogleAuthManager
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_card_performer.*


class EditCardPerformerScreen : BaseFragment(R.layout.screen_edit_card_performer) {
    override fun initialize() {
        initClicks()
        initViews()
    }

    private fun initClicks() {
        add.setOnClickListener { addFragment(AddCardsScreen()) }
        remove.setOnClickListener { addFragment(RemoveCardsScreen()) }
        close.setOnClickListener { finishFragment() }
    }

    private fun initViews() {
        header.text = "Мои платежные карты"

        val images: ArrayList<Int> = ArrayList()
        images.add(R.drawable.card)
        images.add(R.drawable.card)
        images.add(R.drawable.card)
        sliderView.setImages(images)
    }
}