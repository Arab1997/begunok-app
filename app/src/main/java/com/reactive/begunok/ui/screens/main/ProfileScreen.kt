package com.reactive.begunok.ui.screens.main

import android.annotation.SuppressLint
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity.Companion.googleAuthManager
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.extensions.inDevelopment
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.screen_profile.*

class ProfileScreen : BaseFragment(R.layout.screen_profile) {

    override fun initialize() {

        setData()

        initClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        sharedManager.user?.let {
            userId.text = "ID: ${it.id}"
            name.text = it.name
            email.setText(it.email)
            phone.setText(it.phone)
            inProgress.text = it.inProgressOrders.toString()
            finished.text = it.completedOrders.toString()
            it.avatar?.let { img.loadImage(it) }
            it.city?.let { city.text = it }
        }
    }

    private fun initClicks() {
        reviews.setOnClickListener { inDevelopment(requireContext()) } // todo
        support.setOnClickListener { inDevelopment(requireContext()) } // todo
        rules.setOnClickListener { inDevelopment(requireContext()) } // todo

        finishedLayout.setOnClickListener {
            addFragment(OrdersScreen.newInstance(Constants.DONE))
        }

        inProgressLayout.setOnClickListener {
            addFragment(OrdersScreen.newInstance(Constants.ALL_EXCEPT_DONE))
        }

        logout.setOnClickListener {
            viewModel.logout()
            googleAuthManager?.logOut { mainActivity.finish() }
            mainActivity.finish()
        }
    }
}