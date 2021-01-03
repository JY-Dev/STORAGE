package com.example.storage.data.tag

import com.example.storage.data.Repository
import com.example.storage.model.TagData

class TagRepositoryImpl(private val tagDao: TagDao) : Repository<TagData> {
    override suspend fun insert(data: TagData) = tagDao.insert(data)

    override suspend fun update(data: TagData) = tagDao.update(data)

    override suspend fun delete(data: TagData) = tagDao.delete(data)

    override suspend fun getData(): MutableList<TagData> = tagDao.getAllData()

    override suspend fun getDataFromKey(key : Any): TagData? = tagDao.getDataFromTag(key as String)
}