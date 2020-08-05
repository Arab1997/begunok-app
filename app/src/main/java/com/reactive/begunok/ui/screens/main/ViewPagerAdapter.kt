package com.reactive.begunok.ui.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): BaseFragment {
        var fragment: BaseFragment? =null
        if (position == 0)
        {
            fragment =RegistrationScreen()
        }
        else (position == 1)
        {
            fragment =LoginScreen()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2

    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0)
        {
            title = "войти"
        }
        else if (position == 1)
        {
            title = "зарегистрироваться"
        }
        return title
    }

}