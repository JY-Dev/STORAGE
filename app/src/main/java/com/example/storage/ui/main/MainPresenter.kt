package com.example.storage.ui.main

import com.example.storage.base.BasePresenter
import com.example.storage.model.TagData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(val mView : MainContract.View) : MainContract.Presenter, BasePresenter() {
    override fun getImageData(){
        CoroutineScope(Dispatchers.Main).launch{
            val imageData = loadImageData()
            mView.setImage(imageData)
            mView.setStory(imageData.filter { it.story }.toMutableList())
            getTagData()
        }
    }

    override fun tagUpdate(tagData: TagData) {
        CoroutineScope(Dispatchers.IO).launch {
            tagRepository.update(tagData)
        }
    }

    private suspend fun loadTagData() =
        withContext(Dispatchers.IO){
            tagRepository.getData()
        }

    private suspend fun loadImageData() =
        withContext(Dispatchers.IO){
            imageRepository.getData()
        }

    private suspend fun getTagData() {
        mView.setTag(loadTagData())
    }
}