package com.reactive.begunok.ui.screens.auth

import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.RegisterModel
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.utils.extensions.blockClickable
import kotlinx.android.synthetic.main.screen_choose_mode.*


class ChooseModeScreen : BaseFragment(R.layout.screen_choose_mode) {

    private var request = false
    override fun initialize() {

        client.setOnClickListener {
            it.blockClickable()
            open(true)
        }

        executor.setOnClickListener {
            it.blockClickable()
            open(false)
        }
    }

    private fun open(client: Boolean) {
        if (!client && RegisterModel.documents == null) {
            addFragment(AlertScreen())
            return
        }
        RegisterModel.client = client

        request = true
        showProgress(true)
        viewModel.register()
    }

    override fun observe() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (request) {
                replaceFragment(BottomNavScreen(), id = viewModel.parentLayoutId)
                showProgress(false)
                RegisterModel.clear()
                request = false
            }
        })
    }
}