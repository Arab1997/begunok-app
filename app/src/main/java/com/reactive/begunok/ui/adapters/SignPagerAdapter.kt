package com.reactive.begunok.ui.adapters

import android.os.Parcelable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.auth.LoginScreen
import com.reactive.begunok.ui.screens.auth.RegisterScreen

class SignPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): BaseFragment {
        return if (position == 0) LoginScreen()
        else RegisterScreen()
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "войти"
        else "зарегистрироваться"
    }

    override fun saveState(): Parcelable? = null
    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        try {
            super.restoreState(state, loader)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}