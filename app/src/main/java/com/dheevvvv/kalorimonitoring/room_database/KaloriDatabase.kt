package com.dheevvvv.kalorimonitoring.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.CoroutineContext


@Database(entities = [MakananDikonsumsiData::class], version = 2)
abstract class KaloriDatabase:RoomDatabase() {

    abstract fun makananDikonsumsiDao(): MakananDikonsumsiDAO

    companion object {

        private var INSTANCE: KaloriDatabase? = null

        fun getInstance(context: Context): KaloriDatabase? {
            if (INSTANCE == null) {
                synchronized(KaloriDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, KaloriDatabase::class.java, "Kalori.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}