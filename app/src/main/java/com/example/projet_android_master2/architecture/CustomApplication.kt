package com.example.projet_android_master2.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication : Application() {
    companion object {
        lateinit var instance: CustomApplication
    }

    val mApplicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "catalogue"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
