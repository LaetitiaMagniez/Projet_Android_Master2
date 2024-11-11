package com.example.projet_android_master2.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @Expose
    @SerializedName("data")
    val data: AnimeDataDto
)

data class AnimeDataDto(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("images")
    val images: Images,

    @Expose
    @SerializedName("genres")
    val genres: List<Genres>
)

data class Images(
    @Expose
    @SerializedName("jpg")
    val jpg: ImageDetails
)
data class ImageDetails(
    @Expose
    @SerializedName("image_url")
    val imageUrl: String
)

data class Genres(
    @Expose
    @SerializedName("name")
    val genre: String
)

fun AnimeDataDto.toRoom(): AnimeEntity {
    return AnimeEntity(
        title = title,
        poster = images.jpg.imageUrl,
        genres = genres.joinToString(", ") { it.genre },
        date = System.currentTimeMillis()
    )
}
