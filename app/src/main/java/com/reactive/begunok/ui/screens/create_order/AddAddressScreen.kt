package com.reactive.begunok.ui.screens.create_order

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.CreateOrderModel
import com.reactive.begunok.ui.adapters.CitiesAdapter
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.common.RxEditText
import com.reactive.begunok.utils.extensions.disable
import com.reactive.begunok.utils.extensions.enable
import com.reactive.begunok.utils.extensions.gone
import com.reactive.begunok.utils.extensions.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.screen_add_address.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AddAddressScreen : BaseFragment(R.layout.screen_add_address) {

    private val calendar = Calendar.getInstance()
    private var cost = 100
    private lateinit var adapter: CitiesAdapter

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

        price.text = "$cost грн"
        date.setText(sdf.format(calendar.time))

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                cost = seekBar!!.progress + 100
                price.text = "$cost грн"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        date.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
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

        next.disable()

        initSearch()
    }

    private fun initSearch() {

        adapter = CitiesAdapter {
            city.setText(it)
            recycler.gone()
        }
        recycler.adapter = adapter

        RxEditText.getTextWatcherObservable(city).apply {
            debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { text ->
                    if (text.length >= 2) {
                        ArrayList(Constants.cities.filter {
                            it.toLowerCase().contains(text.toLowerCase())
                        }).let {
                            if (it.isNotEmpty()) {
                                adapter.setData(it)
                                recycler.visible()
                            }
                        }
                    } else if (text.isEmpty()) {
                        hideKeyboard()
                        recycler.gone()
                        adapter.setData(arrayListOf())
                        showProgress(false)
                    }
                }
        }
    }

    private fun check() {
        next.disable()

        if (task.text.toString().isEmpty()) {
            task.error = getString(R.string.field_is_empty)
            return
        }

        if (task.text.toString().length < 20) {
            task.error = getString(R.string.minimum_text_length, 20.toString())
            return
        }

        if (city.text.toString().isEmpty()) {
            city.error = getString(R.string.field_is_empty)
            return
        }

        if (address.text.toString().isEmpty()) {
            address.error = getString(R.string.field_is_empty)
            return
        }

        if (date.text.toString().isEmpty()) {
            date.error = getString(R.string.field_is_empty)
            return
        }

        next.enable()
    }
}