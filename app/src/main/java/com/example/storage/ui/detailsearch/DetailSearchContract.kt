package com.example.storage.ui.detailsearch

import com.example.storage.model.ImageData

interface DetailSearchContract {
    interface View{
    }

    interface Presenter{
        suspend fun deleteTag(imgData : ImageData, tag : String)
        suspend fun storyUpdate(imageData : ImageData , isStory : Boolean)
    }
}