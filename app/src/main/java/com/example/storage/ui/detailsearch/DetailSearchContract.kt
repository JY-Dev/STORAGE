package com.example.storage.ui.detailsearch

import com.example.storage.model.ImageData

interface DetailSearchContract {
    interface View{
    }

    interface Presenter{
        suspend fun deleteTag(imgUri : String, tag : String)
        suspend fun storyUpdate(imageData : ImageData , isStory : Boolean)
    }
}