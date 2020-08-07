package com.reactive.begunok.ui.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.auth.LoginScreen
import com.reactive.begunok.ui.screens.auth.RegistrationScreen

class SignPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): BaseFragment {
        if (position == 0) {
            return LoginScreen()
        }
        return RegistrationScreen()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "войти"
        } else if (position == 1) {
            title = "зарегистрироваться"
        }
        return title
    }

}