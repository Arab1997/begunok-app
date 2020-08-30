package com.reactive.begunok.ui.screens.main

import android.annotation.SuppressLint
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.activities.MainActivity.Companion.googleAuthManager
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.inDevelopment
import com.reactive.begunok.utils.extensions.loadImage
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.screen_profile.*

class ProfileScreen : BaseFragment(R.layout.screen_profile) {

    override fun initialize() {

        setData()

        initViews()

        initClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        sharedManager.user.let {
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
        reviews.setOnClickListener { inDevelopment(requireContext()) }
        support.setOnClickListener { inDevelopment(requireContext()) }
        rules.setOnClickListener { inDevelopment(requireContext()) }

        finishedLayout.setOnClickListener {
            addFragment(OrdersScreen.newInstance(Constants.DONE))
        }

        inProgressLayout.setOnClickListener {
            addFragment(OrdersScreen.newInstance(Constants.ALL_EXCEPT_DONE))
        }

        logout.setOnClickListener {
            viewModel.logout()
            googleAuthManager?.logOut { mainActivity.finish() }
        }
    }

    private fun initViews() {
        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        phone.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        card.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
    }

    private fun check() {
        val emailAddress = email.text.toString()

        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        if (phone.text.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }
        if (card.text.toString().length < 16) {
            card.error = getString(R.string.card_number_error)
            return
        }
    }


}