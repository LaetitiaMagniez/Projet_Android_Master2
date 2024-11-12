package com.example.projet_android_master2.ui.model

import com.example.projet_android_master2.data.model.AnimeEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed interface AnimeObject {
    data class Anime(
        val title : String,
        val poster: String,
    ) : AnimeObject

    data class Header(
        val date: String
    ): AnimeObject

    data class Footer(
        val genres: List<String>
    ) : AnimeObject
}

fun List<AnimeEntity>.toUi(): List<AnimeObject> {
    val animeObjects = mutableListOf<AnimeObject>()
    val sdfDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val sdfHour = SimpleDateFormat("HH", Locale.getDefault())

    val groupedAnimes = this.groupBy {
        val date = Date(it.date)
        sdfDate.format(date)
    }

    groupedAnimes.toSortedMap().forEach { (date, animes) ->
        animeObjects.add(AnimeObject.Header(date = date))

        val groupedByHour = if (date == sdfDate.format(Date())) {
            animes.groupBy {
                val date = Date(it.date)
                sdfHour.format(date)
            }
        } else {
            mapOf(date to animes)
        }

        groupedByHour.forEach { (hour, animesAtHour) ->
            if (date == sdfDate.format(Date())) {
                animeObjects.add(AnimeObject.Header(date = "$date - Heure: ${hour}h"))
            }

            val groupedByGenres = mutableMapOf<String, MutableList<AnimeEntity>>()

            animesAtHour.forEach { animeEntity ->
                val genresKey = animeEntity.genres.split(", ")
                    .sorted()
                    .joinToString(", ")

                if (groupedByGenres.containsKey(genresKey)) {
                    groupedByGenres[genresKey]?.add(animeEntity)
                } else {
                    groupedByGenres[genresKey] = mutableListOf(animeEntity)
                }
            }

            groupedByGenres.forEach { (genresKey, animesByGenres) ->
                animesByGenres.forEach { animeEntity ->
                    animeObjects.add(AnimeObject.Anime(
                        title = animeEntity.title,
                        poster = animeEntity.poster
                    ))
                }

                animeObjects.add(AnimeObject.Footer(
                    genres = genresKey.split(", ").toList()
                ))
            }
        }
    }

    return animeObjects
}
