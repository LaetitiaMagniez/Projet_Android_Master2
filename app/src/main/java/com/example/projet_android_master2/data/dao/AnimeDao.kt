package com.example.projet_android_master2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projet_android_master2.data.model.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime_data_table ORDER BY title ASC")
    fun selectAll(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animeEntity: AnimeEntity)

    @Query("DELETE FROM anime_data_table")
    fun deleteAll()
}
