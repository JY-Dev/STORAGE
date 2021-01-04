package com.example.storage.ui.main

import com.example.storage.model.ImageData
import com.example.storage.model.TagData

interface MainContract {
    interface View {
        suspend fun setImage(images: MutableList<ImageData>)
        suspend fun setStory(images: MutableList<ImageData>)
        suspend fun setTag(tags: MutableList<TagData>)
    }

    interface Presenter {
        suspend fun getImageData()
        suspend fun tagUpdate(tagData: TagData)
        suspend fun getTagData()
    }
}