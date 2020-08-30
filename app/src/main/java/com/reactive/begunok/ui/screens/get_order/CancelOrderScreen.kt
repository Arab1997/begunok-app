package com.reactive.begunok.ui.screens.get_order

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.CancelAdapter
import com.reactive.begunok.ui.adapters.CancelData
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.extensions.toast
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_cancel_order.*

class CancelOrderScreen : BaseFragment(R.layout.screen_cancel_order) {

    companion object {
        private var orderId: Int = 0
        fun newInstance(orderId: Int): CancelOrderScreen {
            this.orderId = orderId
            return CancelOrderScreen()
        }
    }

    private var request = false
    private lateinit var adapter: CancelAdapter
    private var data = arrayListOf<CancelData>()
    private var checkedMessage = ""

    override fun initialize() {
        title.text = "Отмена заявки"

        close.setOnClickListener { finishFragment() }

        data = arrayListOf(
            CancelData("Заявка уже не актуальна"),
            CancelData("Не могу связаться с заказчиком"),
            CancelData("Заявка выполнена другим исполнителем"),
            CancelData("Не могу выполнить это задание")
        )

        checkedMessage = data[0].message
        adapter = CancelAdapter {
            data.forEach { d -> d.checked = false }
            data[it].checked = true
            adapter.setData(data)
            checkedMessage = data[it].message
        }
        recycler.adapter = adapter

        cancel.disable()

        message.addTextChangedListener {
            if (it.toString().isEmpty()) {
                message.error = getString(R.string.field_is_empty)
                return@addTextChangedListener
            }
            cancel.enable()
        }
        cancel.setOnClickListener {
            it.blockClickable()
            showProgress(true)
            request = true
            // todo delete executor

        }
    }

    override fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (request /*&& it is */) {
                request = false
                showProgress(false)
                toast(requireContext(), "Заявка успешо отменено")
                finishFragment()
            }
        })
    }
}