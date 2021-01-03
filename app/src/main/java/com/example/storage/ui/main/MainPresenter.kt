package com.example.storage.ui.main

import com.example.storage.base.BasePresenter
import com.example.storage.model.ImageData
import com.example.storage.util.isToday

class MainPresenter(val mView : MainContract.View) : MainContract.Presenter, BasePresenter() {
    override suspend fun getStoryData(){
        mView.setStory(imageRepository.getData())
    }
}