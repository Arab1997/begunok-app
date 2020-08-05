package com.reactive.begunok.ui.screens.main.sign

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.SignPagerAdapter
import kotlinx.android.synthetic.main.fragment_sign.*

class SignFragment: BaseFragment(R.layout.fragment_sign){

    override fun initialize() {
        pager.adapter = SignPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(pager)
    }

}