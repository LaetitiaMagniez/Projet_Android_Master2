package com.example.projet_android_master2.data.remote

import com.example.projet_android_master2.data.model.AnimeResponse
import retrofit2.http.GET

interface AnimeQuoteEndpoint {
    @GET("random/anime")
    suspend fun getRandomQuote() : AnimeResponse
}
