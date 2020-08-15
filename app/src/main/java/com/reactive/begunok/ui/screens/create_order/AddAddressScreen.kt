package com.reactive.begunok.ui.screens.create_order

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.core.widget.addTextChangedListener
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.activities.checkField
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_add_address.*
import java.text.SimpleDateFormat
import java.util.*

class AddAddressScreen : BaseFragment(R.layout.screen_add_address) {

    private val calendar = Calendar.getInstance()
    private var cost = 0

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd.MM.yy")

    @SuppressLint("SetTextI18n")
    override fun initialize() {
        close.setOnClickListener { finishFragment() }

        title.text = "Создать заявку"

        CreateOrderModel.let {
            category.text = it.category!!.name
            subcategory.text = "${it.subCategory!!.name}(${it.jobType!!.name})"
        }

        task.addTextChangedListener { check() }
        city.addTextChangedListener { check() }
        address.addTextChangedListener { check() }

        date.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val cal = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, monthOfYear)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }
                    date.setText(sdf.format(cal.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = System.currentTimeMillis()
            }.show()
        }

        next.setOnClickListener {
            CreateOrderModel.let {
                it.task = task.text.toString()
                it.city = city.text.toString()
                it.address = address.text.toString()
                it.date = date.text.toString()
                it.price = cost
            }

            addFragment(AddPhotoScreen())
        }

        check()
    }

    private fun check() {
        next.disable()

        task.checkField(getString(R.string.field_is_empty))

        if (task.text.toString().length < 20) {
            task.error = getString(R.string.minimum_text_length, 20.toString())
            return
        }

        city.checkField(getString(R.string.field_is_empty))

        address.checkField(getString(R.string.field_is_empty))

        date.checkField(getString(R.string.field_is_empty))

        next.enable()
    }
}