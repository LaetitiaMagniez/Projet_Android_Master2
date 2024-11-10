package com.example.projet_android_master2.architecture

import com.google.gson.GsonBuilder
import com.example.projet_android_master2.data.remote.AnimeQuoteEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeRetrofitBuilder {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun getAnimeQuote(): AnimeQuoteEndpoint = retrofit.create(AnimeQuoteEndpoint::class.java)
}
