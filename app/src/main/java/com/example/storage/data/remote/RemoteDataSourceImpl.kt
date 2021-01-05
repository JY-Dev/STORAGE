package com.example.storage.data.remote

import com.example.storage.data.service.ImageService
import com.example.storage.model.ImageResponse
import okhttp3.MultipartBody

class RemoteDataSourceImpl(private val service : ImageService) : RemoteDataSource {
    override suspend fun getTags(img: MultipartBody.Part): ImageResponse = service.getImageTag(img)
}