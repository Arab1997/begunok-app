package com.reactive.begunok.ui.screens.performers

import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import com.amyu.stack_card_layout_manager.StackCardLayoutManager
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.performers.cards.CardViewModel
import com.reactive.begunok.ui.screens.performers.cards.StackCardAdapter
import com.reactive.begunok.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_remove_cards_performer.*

class RemoveCardsScreen : BaseFragment(R.layout.screen_remove_cards_performer) {

    override fun initialize() {
        initClicks()
        initViews()

        close.setOnClickListener { finishFragment() }
    }

    private fun initClicks() {
        save.setOnClickListener { inDevelopment(requireContext()) }
       // delete.setOnClickListener { addFragment(AlertDeleteCardPerformerScreen()) }
    }

    private fun initViews() {
        header.text = "Удалить платежную карту"


        val adapter = StackCardAdapter(requireContext()).apply {
            onItemClickListener = { cardView, _ ->
              //  val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@RemoveCardsScreen, cardView, cardView.transitionName)
               // startActivityWithOptions(compat) { DetailActivity.createIntent(it, cardViewModel.title.value!!, cardViewModel.background.value!!, cardViewModel.logo.value!!) }
            }
            submitList(
                listOf(
                    CardViewModel(
                        title = MutableLiveData<String>().apply {
                            postValue("Master card")
                        },
                        background = MutableLiveData<Int>().apply {
                            postValue(R.drawable.backgroundd)
                        },
                        logo = MutableLiveData<Int>().apply {
                            postValue(R.drawable.mastercard)
                        }
                    ),
                    CardViewModel(
                        title = MutableLiveData<String>().apply {
                            postValue("Master card")
                        },
                        background = MutableLiveData<Int>().apply {
                            postValue(R.drawable.backgroundd)
                        },
                        logo = MutableLiveData<Int>().apply {
                            postValue(R.drawable.mastercard)
                        }
                    ),
                    CardViewModel(
                        title = MutableLiveData<String>().apply {
                            postValue("Visa")
                        },
                        background = MutableLiveData<Int>().apply {
                            postValue(R.drawable.backgroundd)
                        },
                        logo = MutableLiveData<Int>().apply {
                            postValue(R.drawable.visa)
                        }
                    )
                )
            )
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StackCardLayoutManager(5)
    }

}