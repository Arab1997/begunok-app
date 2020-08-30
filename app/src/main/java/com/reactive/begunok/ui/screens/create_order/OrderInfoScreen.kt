package com.reactive.begunok.ui.screens.create_order

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.SuccessResp
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.network.models.Order
import com.reactive.begunok.ui.adapters.ImgAdapter
import com.reactive.begunok.ui.adapters.RequestsAdapter
import com.reactive.begunok.ui.screens.get_order.CancelOrderScreen
import com.reactive.begunok.ui.screens.get_order.ExecutorDetailScreen
import com.reactive.begunok.ui.screens.get_order.RequestOrderScreen
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.*
import com.reactive.begunok.utils.setOrderStatus
import com.reactive.begunok.utils.validators.TextValidator
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_order_info.*
import okhttp3.MultipartBody
import java.io.File

class OrderInfoScreen : BaseFragment(R.layout.screen_order_info) {

    companion object {
        private var order: Order? = null
        fun newInstance(order: Order): OrderInfoScreen {
            this.order = order
            return OrderInfoScreen()
        }
    }

    private var editRequest = false
    private var createRequest = false
    private var deleteRequest = false
    private lateinit var adapter: ImgAdapter
    private lateinit var requestsAdapter: RequestsAdapter

    override fun initialize() {

        initCommonView()

        initClicks()
    }

    private fun initClicks() {

        close.setOnClickListener { finishFragment() }

        create.setOnClickListener { v ->
            v.blockClickable()

            CreateOrderModel.let {
                it.email = email.text.toString()
                it.phone = phone.text.toString()
            }

            showProgress(true)

            createRequest = true

            createOrder()

        }

        delete.setOnClickListener {
            deleteRequest = true
            viewModel.deleteOrder(order!!.id)
        }

        edit.setOnClickListener {
            editRequest = true
            showProgress(true)
            viewModel.deleteOrder(order!!.id)
        }

        getOrder.setOnClickListener { addFragment(RequestOrderScreen.newInstance(order!!.id)) }
        cancel.setOnClickListener { inDevelopment(requireContext()) } // todo

    }

    private fun initCommonView() {

        adapter = ImgAdapter {
            addFragment(ImgScreen.newInstance(it))
        }
        recycler?.adapter = adapter

        if (order != null) initOrderInfoView()
        else initCreateOrderView()
    }

    @SuppressLint("SetTextI18n")
    private fun initOrderInfoView() {

        title.text = "Заявка"

        order!!.let {
            job.text = it.jobType.name
            desc.text = it.task
            date.text = it.date
            price.text = "${it.price} грн."
            address.text = it.address

            email.setText(it.email)
            phone.setText(it.phone)
        }

        requestLayout.visible()

        requestsAdapter =
            RequestsAdapter(sharedManager.user.id == order!!.user.id) { order, select ->
                when (select) {
                    null -> addFragment(ExecutorDetailScreen.newInstance(order.user, order.message))
                    true -> {
// todo select executor
                    }
                    false -> {
                        addFragment(CancelOrderScreen.newInstance(order.id))
                    }
                }
// todo update order view
            }
        requests.adapter = requestsAdapter

        orderStatus.setOrderStatus(order!!.status)

        if (order!!.user.id == sharedManager.user.id) {
            editOrderView()
        } else {
            requestOrderView()
        }
    }

    private fun editOrderView() {

        delete.visible()
        edit.visible()

        email.isClickable = false
        phone.isClickable = false

        emptyImg.gone()
        emptyText.gone()

    }

    private fun requestOrderView() {
        orderInfoLayout.gone()
        statusTitle.gone()
        orderStatus.gone()
        payment.visible()
        getOrder.visible()
        cancel.gone() // todo
    }

    @SuppressLint("SetTextI18n")
    private fun initCreateOrderView() {

        title.text = "Создать заявку"

        CreateOrderModel.let {
            job.text = it.jobType!!.name
            desc.text = it.task
            date.text = it.date
            address.text = it.address
            price.text = "${it.price} грн"
            adapter.setData(it.photos)
        }

        email.setText(sharedManager.user.email)
        phone.setText(sharedManager.user.phone)

        email.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })

        check()
    }

    private fun check() {
        create.disable()

        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.invalid_email_adress)
            return
        }
        if (phone.rawText.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }

        create.enable()
    }

    private fun createOrder() {

        val files = getFiles()
        val partMap = hashMapOf<String, Any>()

        CreateOrderModel.let {
            partMap["category"] = it.category!!.id
            partMap["subCategory"] = it.subCategory!!.id
            partMap["jobType"] = it.jobType!!.id
            partMap["task"] = it.task
            partMap["description"] = it.task
            partMap["city"] = it.city
            partMap["address"] = it.address
            partMap["date"] = it.date
            partMap["price"] = it.price
            partMap["email"] = it.email
            partMap["phone"] = it.phone
        }

        viewModel.createOrder(partMap, files)
    }

    private fun getFiles(): List<MultipartBody.Part> {
        return CreateOrderModel.photos.map { viewModel.createFileMultipart("photos", File(it)) }
    }

    override fun observe() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (it is Order) {
                showProgress(false)

                if (createRequest) {
                    createRequest = false
                    CreateOrderModel.clear()
                    popInclusive()
                    toast(requireContext(), "Заказ создано")
                }

                if (editRequest) {
                    editRequest = false
                    toast(requireContext(), "Заказ обновлено")
                }
            }

            if (it is SuccessResp && deleteRequest) {
                deleteRequest = false
                toast(requireContext(), "Заказ удалено")
                finishFragment()
            }
        })

        viewModel.orderRequests.observe(viewLifecycleOwner, Observer {
            emptyImg.showGone(it.isEmpty())
            emptyText.showGone(it.isEmpty())
            requestsAdapter.setData(it)

            if (it.any { it.user.id == sharedManager.user.id }) {
                cancel.visible()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        order = null
    }
}