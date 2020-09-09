package com.reactive.begunok.ui.screens.performers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_rate_performer.*

class RateScreen : BaseFragment(R.layout.screen_rate_performer) {

    override fun initialize() {

        initClicks()

        initViews()

    }

    private fun initClicks() {
        close.setOnClickListener { finishFragment() }

        like.setOnClickListener { inDevelopment(requireContext()) } // todo

        dislike.setOnClickListener { inDevelopment(requireContext()) } // todo

        send.setOnClickListener { inDevelopment(requireContext()) } // todo
    }


    private fun initViews() {
        header.text = "Оценить исполнителя"
    }
}