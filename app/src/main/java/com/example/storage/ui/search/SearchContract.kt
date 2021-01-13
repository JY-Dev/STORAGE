package com.example.storage.ui.search

import com.example.storage.model.ImageData

interface SearchContract {
    interface View{
        fun refreshImage(images:MutableList<ImageData>)
    }

    interface Presenter{
        fun getImageData()
    }
}