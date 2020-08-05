package com.reactive.begunok.ui.screens.main

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.google.android.material.tabs.TabLayout
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment

class Login: BaseFragment(R.layout.screen_registr) {
    var tabLayout:TabLayout?=null
    var viewPager: ViewPager?=null
    var viewPagerAdapter: ViewPagerAdapter?=null

    override fun initialize() {

    }

}