package com.example.storage.data.image

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import com.example.storage.util.Converters

@Database(entities = [ImageData::class], version = 1)
@TypeConverters(Converters::class)
abstract class ImageDB : RoomDatabase() {
    abstract fun getDao() : ImageDao
    object Factory{
        private const val dbName = "image.db"
        fun create(context: Context) : ImageDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ImageDB::class.java,
                dbName
            ).build()
        }
    }
}