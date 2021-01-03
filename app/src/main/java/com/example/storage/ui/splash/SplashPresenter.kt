package com.example.storage.ui.splash

import android.util.Log
import com.example.storage.base.BasePresenter
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import kotlinx.coroutines.*
import kotlin.random.Random

class SplashPresenter(val mView: SplashContract.View) : SplashContract.Presenter, BasePresenter() {
    val tagMock = mutableListOf(
        mutableListOf("test", "ok", "check", "bird"),
        mutableListOf("bird", "ok", "test", "seoul"),
        mutableListOf("now", "time"),
        mutableListOf("dog", "animal", "bird", "ok"),
        mutableListOf("me", "test", "seoul")
    )

    override suspend fun dataCheck(imageUri: String, date: Long) {
        withContext(Dispatchers.IO) {
            if(imageRepository.getDataFromKey(imageUri)==null){
                getTags().apply {
                    updateTagCount(this)
                    insertImage(imageUri,date,this)
                }
            }
        }
    }

    /**
     * 통신으로 받아오기
     */
    private suspend fun getTags() : MutableList<String> = tagMock[Random.nextInt(tagMock.size)]

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