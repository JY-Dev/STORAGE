package com.example.storage.ui.splash

import com.example.storage.base.BasePresenter
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.random.Random

class SplashPresenter(val mView: SplashContract.View) : SplashContract.Presenter, BasePresenter() {
    val tagMock = mutableListOf(
        mutableListOf("test", "ok", "check", "bird"),
        mutableListOf("bird", "ok", "test", "seoul"),
        mutableListOf("now", "time"),
        mutableListOf("dog", "animal", "bird", "ok"),
        mutableListOf("me", "test", "seoul")
    )

    override suspend fun dataCheck(imageUri: String, date: Long , file: File) {
        if(imageRepository.getDataFromKey(imageUri)==null){
            getMockTags().apply {
                updateTagCount(this)
                insertImage(imageUri,date,this)
            }
        }
    }

    /**
     * 통신으로 받아오기
     */
    private suspend fun getTags(file: File) : MutableList<String> {
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(),file)
        val body = MultipartBody.Part.createFormData("img",file.name,requestFile)
        return dataSource.getTags(body).tags
    }

    private fun getMockTags() : MutableList<String> = tagMock[Random.nextInt(tagMock.size)]


    private suspend fun updateTagCount(tags: MutableList<String>) {
        tags.forEach { tag ->
            if (tagRepository.getDataFromKey(tag) == null) insertTag(tag)
            tagRepository.getDataFromKey(tag)?.run {
                count += 1
                println("tag=$tag count=$count")
                updateTag(this)
            }
        }
    }

    private suspend fun insertImage(imageUri: String, date: Long, tags: MutableList<String>) {
        imageRepository.insert(ImageData(imageUri, false, tags, date))
    }


    private suspend fun insertTag(tag: String) {
        tagRepository.insert(TagData(tag, 1, false))
    }


    private suspend fun updateTag(data: TagData) =
        tagRepository.update(data)
}