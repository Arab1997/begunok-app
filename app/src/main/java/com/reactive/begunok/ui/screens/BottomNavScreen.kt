package com.reactive.begunok.ui.screens

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.main.BlankScreen
import kotlinx.android.synthetic.main.screen_bottom_nav.*

class BottomNavScreen : BaseFragment(R.layout.screen_bottom_nav) {

    private var bottomFragments = arrayListOf<Fragment>(
        BlankScreen(),
        BlankScreen(),
        BlankScreen(),
        BlankScreen(),
        BlankScreen()
    )

    override fun initialize() {

        setHasOptionsMenu(true)

        bottomNav.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.home -> {
                    selectFragment(0)
                    true
                }
                R.id.path -> {
                    selectFragment(1)
                    true
                }
                R.id.me -> {
                    selectFragment(2)
                    true
                }
                R.id.breakthrough -> {
                    selectFragment(3)
                    true
                }
                R.id.events -> {
                    selectFragment(4)
                    true
                }
                else -> false
            }
        }

        selectFragment(0)

    }

    private fun selectFragment(pos: Int) {
        replaceFragment(bottomFragments[pos])
    }

    override fun observe() {
    }
}
