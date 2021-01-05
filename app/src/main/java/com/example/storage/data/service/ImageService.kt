package com.example.storage.data.service

import com.example.storage.model.ImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageService {
    @Multipart
    @POST("api/test")
    suspend fun getImageTag(@Part img : MultipartBody.Part) : ImageResponse
}