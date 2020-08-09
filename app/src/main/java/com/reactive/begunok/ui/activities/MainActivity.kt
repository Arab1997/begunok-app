package com.reactive.begunok.ui.activities

import android.view.KeyEvent
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseActivity
import com.reactive.begunok.base.BaseViewModel
import com.reactive.begunok.base.initialFragment
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.ui.screens.auth.AuthScreen
import com.reactive.begunok.ui.screens.customer.ChooseAddressScreen
import com.reactive.begunok.ui.screens.splash.SplashScreen
import com.reactive.begunok.utils.extensions.showGone
import com.reactive.begunok.utils.preferences.SharedManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    override fun onActivityCreated() {
        viewModel.apply {
            parentLayoutId = R.id.fragmentContainer
            navLayoutId = R.id.navContainer

            fetchData()
        }
        debug()
//        startFragment()
    }

    private fun debug() = initialFragment(ChooseAddressScreen())

    private fun startFragment() {
        initialFragment(
            if (sharedManager.token.isEmpty()) SplashScreen().apply {
                setListener { initialFragment(AuthScreen(), true) }
            }
            else BottomNavScreen(), true
        )
    }

    fun showProgress(show: Boolean) {
        progressBar.showGone(show)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> return false
                KeyEvent.KEYCODE_VOLUME_DOWN -> return false
            }
        }
        return super.dispatchKeyEvent(event)
    }
}
