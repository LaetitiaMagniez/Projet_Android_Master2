package com.example.projet_android_master2.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeDataDto(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("images")
    val images: Images
)

data class AnimeResponse(
    @Expose
    @SerializedName("data")
    val data: AnimeDataDto
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

fun AnimeDataDto.toRoom(): AnimeEntity {
    return AnimeEntity(
        title = title,
        poster = images.jpg.imageUrl
    )
}
