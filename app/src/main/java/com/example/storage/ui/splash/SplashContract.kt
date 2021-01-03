package com.example.storage.ui.splash

import com.example.storage.data.Repository
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import io.reactivex.disposables.CompositeDisposable

interface SplashContract {
    interface View{
    }

    interface  Presenter{
        //fun imageDataCheck(imageUri : String , date : Long)
        suspend fun dataCheck(imageUri: String, date: Long)
    }
}