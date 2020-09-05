package com.reactive.begunok.ui.screens.get_order

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.User
import com.reactive.begunok.utils.extensions.*
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.screen_executor_details.*

class ExecutorDetailScreen : BaseFragment(R.layout.screen_executor_details) {

    companion object {
        private var user: User? = null
        private var message: String = ""
        fun newInstance(user: User, message: String): ExecutorDetailScreen {
            this.user = user
            this.message = message
            return ExecutorDetailScreen()
        }
    }

    private var request = false

    @SuppressLint("SetTextI18n")
    override fun initialize() {

        user?.let {
            userId.text = "ID: ${it.id}"
            name.text = it.name

            inProgress.text = it.inProgressOrders.toString()
            finished.text = it.completedOrders.toString()

            it.avatar?.let { img.loadImage(it) }
            it.city?.let { city.text = it }
        }

        desc.text = message

        reviews.setOnClickListener { inDevelopment(requireContext()) } // todo

        back.setOnClickListener { finishFragment() }

        select.setOnClickListener {
            // todo choose
            it.blockClickable()
            request = true
        }

        finishedText.text = "О исполнителе"
        inProgressText.text = "Отзывов 0" // todo

        back.visible()
        reviewsText.gone()
        inProgress.gone()
        finished.gone()
    }

    override fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (request /*todo*/) {
                request = false
                showProgress(false)
                toast(requireContext(), "Исполнитель успешно выбран")
                finishFragment()
            }
        })
    }
}