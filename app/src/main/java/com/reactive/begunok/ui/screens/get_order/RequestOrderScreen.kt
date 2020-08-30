package com.reactive.begunok.ui.screens.get_order

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.OrderRequests
import com.reactive.begunok.utils.extensions.*
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_request_order.*

class RequestOrderScreen : BaseFragment(R.layout.screen_request_order) {

    companion object {
        private var orderId: Int = 0
        fun newInstance(orderId: Int): RequestOrderScreen {
            this.orderId = orderId
            return RequestOrderScreen()
        }
    }

    private var request = false
    override fun initialize() {

        title.text = "Заявка"

        send.visible()
        send.disable()
        send.setOnClickListener {
            it.blockClickable()
            sendRequest()
        }

        message.addTextChangedListener {
            if (it.toString().length < 10) {
                message.error = getString(R.string.minimum_text_length, 10.toString())
                return@addTextChangedListener
            }
            send.enable()
        }

        close.setOnClickListener { finishFragment() }
    }

    private fun sendRequest() {
        request = true
        showProgress(true)
        viewModel.requestForOrder(orderId, message.text.toString())
    }

    override fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (request && it is OrderRequests) {
                request = false
                showProgress(false)
                toast(requireContext(), "Ваша заявка успешно отправлено")
                finishFragment()
            }
        })
    }
}