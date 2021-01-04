package com.example.storage.ui.main

import com.example.storage.base.BasePresenter
import com.example.storage.model.TagData

class MainPresenter(val mView : MainContract.View) : MainContract.Presenter, BasePresenter() {
    override suspend fun getImageData(){
        val imageData = imageRepository.getData()
        mView.setImage(imageData)
        mView.setStory(imageData)
    }

    override suspend fun tagUpdate(tagData: TagData) {
        tagRepository.update(tagData)
    }

    override suspend fun getTagData() {
        mView.setTag(tagRepository.getData())
    }
}