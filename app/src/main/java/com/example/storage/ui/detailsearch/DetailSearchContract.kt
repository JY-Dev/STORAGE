package com.example.storage.ui.detailsearch

import com.example.storage.model.ImageData

interface DetailSearchContract {
    interface View{
    }

    interface Presenter{
        fun deleteTag(imgData : ImageData, tag : String)
        fun storyUpdate(imageData : ImageData , isStory : Boolean)
    }
}