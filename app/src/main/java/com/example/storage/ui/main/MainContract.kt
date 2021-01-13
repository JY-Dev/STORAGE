package com.example.storage.ui.main

import com.example.storage.model.ImageData
import com.example.storage.model.TagData

interface MainContract {
    interface View {
        fun setImage(images: MutableList<ImageData>)
        fun setStory(images: MutableList<ImageData>)
        fun setTag(tags: MutableList<TagData>)
    }

    interface Presenter {
        fun getImageData()
        fun tagUpdate(tagData: TagData)
    }
}