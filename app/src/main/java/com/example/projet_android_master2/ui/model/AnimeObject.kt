package com.example.projet_android_master2.ui.model

import com.example.projet_android_master2.data.model.AnimeEntity

data class AnimeObject(
    val title : String,
    val poster: String,
)

fun List<AnimeEntity>.toUi(): List<AnimeObject> {
    return map { eachEntity ->
        AnimeObject(
            title = eachEntity.title,
            poster = eachEntity.poster
        )
    }
}
