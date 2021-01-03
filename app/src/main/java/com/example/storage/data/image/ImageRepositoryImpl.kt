package com.example.storage.data.image

import com.example.storage.data.Repository
import com.example.storage.model.ImageData
import com.example.storage.model.TagData

class ImageRepositoryImpl(private val imageDao: ImageDao) : Repository<ImageData> {
    override suspend fun insert(data: ImageData) = imageDao.insert(data)

    override suspend fun update(data: ImageData) = imageDao.update(data)

    override suspend fun delete(data: ImageData) = imageDao.delete(data)

    override suspend fun getData(): MutableList<ImageData> = imageDao.getAllData()

    override suspend fun getDataFromKey(key: Any): ImageData? = imageDao.getDataFromKey(key as String)
}