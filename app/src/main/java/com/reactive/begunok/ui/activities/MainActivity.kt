package com.reactive.begunok.ui.activities

import android.view.KeyEvent
import android.widget.EditText
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseActivity
import com.reactive.begunok.base.BaseViewModel
import com.reactive.begunok.base.initialFragment
import com.reactive.begunok.network.User
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.ui.screens.auth.AuthScreen
import com.reactive.begunok.ui.screens.splash.SplashScreen
import com.reactive.begunok.utils.extensions.showGone
import com.reactive.begunok.utils.preferences.SharedManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    companion object {
        var customer: Boolean = false
        val data = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, "")
    }

    override fun onActivityCreated() {

        sharedManager.token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXBpIl0sInVzZXJfbmFtZSI6IjExMTEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNjAzNTM4OTE2LCJhdXRob3JpdGllcyI6WyJVU0VSIl0sImp0aSI6Ijk5NjUyMGZkLWYwNzAtNGVjZS1hOTFiLTQ2YWUyMDc3ZGNiNCIsImNsaWVudF9pZCI6ImFuZHJvaWQifQ.IgXD-sMOsnjxiSDsnEDxSHsl1oDWymeATe-QWiv5xQo" // todo
        sharedManager.user = User(12, "MukhammadRasul", "1231230998", "asldjk@asdjk.ad", true)

        viewModel.apply {
            parentLayoutId = R.id.fragmentContainer
            authLayoutId = R.id.registerContainer
            navLayoutId = R.id.navContainer

            fetchData()
        }

        debug()
//        startFragment()
    }

    private fun debug() = initialFragment(BottomNavScreen())

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

fun EditText.checkField(error: String) {
    if (this.text.toString().isEmpty()) {
        this.error = error
        return
    }
}