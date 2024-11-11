package com.example.projet_android_master2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_data_table")
data class AnimeEntity(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "date")
    val date: Long
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
