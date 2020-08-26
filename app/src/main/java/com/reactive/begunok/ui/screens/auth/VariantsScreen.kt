package com.reactive.begunok.ui.screens.auth

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.adapters.MailsAdapter
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_variants.*

class VariantsScreen : BaseFragment(R.layout.screen_variants) {

    private lateinit var adapter: MailsAdapter
    override fun initialize() {

        current.text = sharedManager.mails.first().email

        var mails = ArrayList(sharedManager.mails.sortedByDescending { it.lastDate })
        if (mails.size > 5) mails = ArrayList(mails.subList(0, 5))
        adapter = MailsAdapter {
            viewModel.shared.value = it
            finishFragment()
        }.apply { setData(mails) }
        recycler.adapter = adapter

        dim.setOnClickListener { finishFragment() }
        politics.setOnClickListener { inDevelopment(requireContext()) }
        rules.setOnClickListener { inDevelopment(requireContext()) }
    }
}