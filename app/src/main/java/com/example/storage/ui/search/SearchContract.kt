package com.example.storage.ui.search

import com.example.storage.model.ImageData

interface SearchContract {
    interface View{
        suspend fun refreshImage(images:MutableList<ImageData>)
    }

    interface Presenter{
        suspend fun getImageData()
    }
}