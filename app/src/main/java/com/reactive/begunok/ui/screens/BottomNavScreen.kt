package com.reactive.begunok.ui.screens

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity
import com.reactive.begunok.ui.screens.main.*
import kotlinx.android.synthetic.main.screen_bottom_nav.*

class BottomNavScreen : BaseFragment(R.layout.screen_bottom_nav) {

    private var bottomFragments = arrayListOf<Fragment>(
        OrdersScreen(),
        BalanceScreen(),
        ProfileScreen(),
        NotificationsScreen(),
        StartScreen()
    )

    override fun initialize() {

        bottomNav.menu.getItem(0).title = getString(
            if (MainActivity.client) R.string.my_orders else R.string.my_job
        )

        bottomNav.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.home -> {
                    selectFragment(0)
                    true
                }
                R.id.balance -> {
                    selectFragment(1)
                    true
                }
                R.id.profile -> {
                    selectFragment(2)
                    true
                }
                R.id.notifications -> {
                    selectFragment(3)
                    true
                }
                R.id.seach -> {
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
