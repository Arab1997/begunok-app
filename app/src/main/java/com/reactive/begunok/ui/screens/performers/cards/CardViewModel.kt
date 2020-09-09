package com.reactive.begunok.ui.screens.performers.cards

import androidx.lifecycle.LiveData


data class CardViewModel(
        val logo: LiveData<Int>,
        val background: LiveData<Int>,
        val title: LiveData<String>
)