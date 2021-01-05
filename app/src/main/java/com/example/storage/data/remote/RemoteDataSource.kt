package com.example.storage.data.remote

import com.example.storage.model.ImageResponse
import okhttp3.MultipartBody

interface RemoteDataSource {
    suspend fun getTags(img : MultipartBody.Part) : ImageResponse
}