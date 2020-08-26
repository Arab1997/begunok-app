package com.reactive.begunok.ui.screens.auth.register

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.network.models.RegisterModel
import com.reactive.begunok.ui.adapters.CitiesAdapter
import com.reactive.begunok.utils.Constants
import com.reactive.begunok.utils.common.RxEditText
import com.reactive.begunok.utils.common.TextWatcherInterface
import com.reactive.begunok.utils.extensions.*
import com.reactive.begunok.utils.validators.TextValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.screen_reg1.*
import java.util.concurrent.TimeUnit


class Registration1Screen : BaseFragment(R.layout.screen_reg1) {

    private lateinit var adapter: CitiesAdapter
    private var selectedCity: String = ""

    override fun initialize() {

        initViews()
    }

    private fun initViews() {
        next.disable()

        adapter = CitiesAdapter {
            selectedCity = it
            city.setText(selectedCity)
        }
        recycler.adapter = adapter

        initSearch()

        next.setOnClickListener { v ->
            v.blockClickable()
            addFragment(Registration2Screen())

            RegisterModel.let {
                it.email = email.text.toString()
                it.name = name.text.toString()
                it.password = passw.text.toString()
                it.phone = "+38" + phone.rawText.toString()
                it.city = city.text.toString()
            }
        }

        name.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
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
        city.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        passw.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
        resPass.addTextChangedListener(object : TextWatcherInterface {
            override fun textChanged(s: String) {
                check()
            }
        })
    }

    private fun initSearch() {

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

        if (name.text.toString().isEmpty()) {
            name.error = getString(R.string.field_is_empty)
            return
        }
        val emailAddress = email.text.toString()
        if (emailAddress.isEmpty() || !TextValidator.isEmail(emailAddress)) {
            email.error = getString(R.string.invalid_email_adress)
            return
        }
        if (phone.rawText.length < 10) {
            phone.error = getString(R.string.phone_is_not_valid)
            return
        }
        if (city.text.toString().isEmpty()) {
            city.error = getString(R.string.field_is_empty)
            return
        }
        if (passw.text.toString().length < 6) {
            passw.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (resPass.text.toString().length < 6) {
            resPass.error = getString(R.string.minimum_password_length_6)
            return
        }
        if (passw.text.toString() != resPass.text.toString()) {
            resPass.error = getString(R.string.the_verified_password_is_not_valid)
            return
        }
        next.enable()
    }

}