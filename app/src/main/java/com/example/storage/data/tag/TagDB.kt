package com.example.storage.data.tag

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.storage.model.TagData

@Database(entities = [TagData::class], version = 1)
abstract class TagDB : RoomDatabase() {
    abstract fun getDao() : TagDao
    object Factory{
        private const val dbName = "tag.db"
        fun create(context: Context) : TagDB {
            return Room.databaseBuilder(
                context.applicationContext,
                TagDB::class.java,
                dbName
            ).build()
        }
    }
}