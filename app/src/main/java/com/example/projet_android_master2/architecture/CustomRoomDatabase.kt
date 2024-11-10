package com.example.projet_android_master2.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projet_android_master2.data.dao.AnimeDao
import com.example.projet_android_master2.data.model.AnimeEntity

@Database(
    entities = [
        AnimeEntity::class
    ],
    version = 4,
    exportSchema = false
)

abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
