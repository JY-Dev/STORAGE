package com.example.storage.data.tag

import androidx.room.*
import com.example.storage.model.TagData

@Dao
interface TagDao {
    /**
     * Get All TodoData
     */
    @Query("SELECT * FROM TagData")
    suspend fun getAllData() : MutableList<TagData>

    /**
     * Update Certain TodoData
     */
    @Update
    suspend fun update(vararg todoData : TagData)

    /**
     * Insert TodoData
     */
    @Insert
    suspend fun insert(vararg todoData: TagData)

    /**
     * Delete TodoData
     */
    @Delete
    suspend fun delete(vararg todoData : TagData)

    @Query("SELECT * FROM TagData WHERE tag =:tag")
    suspend fun getDataFromTag(tag : String) : TagData?
}