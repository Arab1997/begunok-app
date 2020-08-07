package com.reactive.begunok.ui.screens.auth

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.screen_registration.*


class RegistrationScreen : BaseFragment(R.layout.screen_registration) {

    private val countries = arrayListOf<String>("US", "RU", "UZ")

    override fun initialize() {

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countryCode.adapter = adapter

        countryCode.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

        }

    }



}