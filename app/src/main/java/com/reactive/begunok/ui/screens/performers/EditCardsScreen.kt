package com.reactive.begunok.ui.screens.performers

import androidx.viewpager.widget.ViewPager
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_cards.*

class EditCardsScreen : BaseFragment(R.layout.screen_edit_cards) {
    var viewPager: ViewPager? = null

    var imageId =
        arrayOf<Int>(R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card)
    var imagesName = arrayOf("image1", "image2", "image3", "image4")

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

    }
}


