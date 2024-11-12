package com.example.projet_android_master2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_android_master2.ui.model.AnimeObject
import com.example.projet_android_master2.data.repository.AnimeQuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val animeQuoteRepository: AnimeQuoteRepository by lazy { AnimeQuoteRepository() }
    private val _animes: Flow<List<AnimeObject>> get() = animeQuoteRepository.selectAll()

    val anime = _animes

    fun insertNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            animeQuoteRepository.fetchData()
        }
    }

    fun deleteAllQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            animeQuoteRepository.deleteAll()
        }
    }
}
