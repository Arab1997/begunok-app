package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.SignPagerAdapter
import kotlinx.android.synthetic.main.screen_sign.*

class SignScreen : BaseFragment(R.layout.screen_sign) {

    override fun initialize() {

        pager.adapter = SignPagerAdapter(childFragmentManager)

        tabLayout.setupWithViewPager(pager)
    }
}