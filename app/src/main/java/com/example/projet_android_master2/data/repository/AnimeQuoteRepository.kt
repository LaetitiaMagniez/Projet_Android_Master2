package com.example.projet_android_master2.data.repository

import com.example.projet_android_master2.architecture.CustomApplication
import com.example.projet_android_master2.architecture.AnimeRetrofitBuilder
import com.example.projet_android_master2.data.model.AnimeResponse
import com.example.projet_android_master2.data.model.toRoom
import com.example.projet_android_master2.ui.model.AnimeObject
import com.example.projet_android_master2.ui.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeQuoteRepository {
    private val animeDao = CustomApplication.instance.mApplicationDatabase.animeDao()

    suspend fun fetchData() {
        val response: AnimeResponse = AnimeRetrofitBuilder.getAnimeQuote().getRandomQuote()
        val randomCountry = response.data
        animeDao.insert(randomCountry.toRoom())
    }

    fun deleteAll() {
        animeDao.deleteAll()
    }

    fun selectAll(): Flow<List<AnimeObject>> {
        return animeDao.selectAll().map { list ->
            list.toUi()
        }
    }
}
