package com.example.storage.ui.main

import com.example.storage.model.ImageData

interface MainContract {
    interface View {
        suspend fun setStory(storyList : MutableList<ImageData>)
    }
    interface Presenter{
       suspend fun getStoryData()
    }
}