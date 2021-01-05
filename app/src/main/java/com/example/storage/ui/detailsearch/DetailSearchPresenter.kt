package com.example.storage.ui.detailsearch

import com.example.storage.base.BasePresenter
import com.example.storage.model.ImageData

class DetailSearchPresenter() : DetailSearchContract.Presenter , BasePresenter() {
    override suspend fun deleteTag(imgUri : String , tag : String){
        imageRepository.getDataFromKey(imgUri)?.run {
            tags.remove(tag)
            imageRepository.update(this)
        }
    }

    override suspend fun storyUpdate(imageData : ImageData, isStory: Boolean) {
        imageData.apply {
            story = isStory
            imageRepository.update(this)
        }
    }

}