package com.example.storage.data.image

import androidx.room.*
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import io.reactivex.Completable

@Dao
interface ImageDao {
    /**
     * Get All TodoData
     */
    @Query("SELECT * FROM ImageData")
    suspend fun getAllData() : MutableList<ImageData>

    /**
     * Update Certain TodoData
     */
    @Update
    suspend fun update(vararg todoData : ImageData)

    /**
     * Insert TodoData
     */
    @Insert
    suspend fun insert(vararg todoData: ImageData)

    /**
     * Delete TodoData
     */
    @Delete
    suspend fun delete(vararg todoData : ImageData)

    @Query("SELECT * FROM ImageData WHERE imageUri =:key")
    suspend fun getDataFromKey(key : String) : ImageData?
}