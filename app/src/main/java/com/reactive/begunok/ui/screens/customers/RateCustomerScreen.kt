package com.reactive.begunok.ui.screens.customers

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity.Companion.googleAuthManager
import com.reactive.begunok.ui.screens.main.OrdersScreen
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.extensions.inDevelopment
import com.reactive.begunok.utils.extensions.loadImage
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.screen_profile.*

class RateCustomerScreen : BaseFragment(R.layout.screen_rate_customer) {

    override fun initialize() {

        //  initClicks()
        initViews()
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


    private fun initViews() {
        header.text = "Оценить заказчика"
    }

}