package com.example.storage.ui.detailsearch

import com.example.storage.base.BasePresenter
import com.example.storage.model.ImageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailSearchPresenter() : DetailSearchContract.Presenter , BasePresenter() {
    override fun deleteTag(imageData : ImageData , tag : String){
        CoroutineScope(Dispatchers.IO).launch {
            imageData.apply {
                tags.remove(tag)
                imageRepository.update(this)
            }
            tagRepository.getDataFromKey(tag)?.apply {
                if(count > 1) {
                    count-=1
                    tagRepository.update(this)
                } else tagRepository.delete(this)
            }
        }
    }

    override fun storyUpdate(imageData : ImageData, isStory: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            imageData.apply {
                story = isStory
                imageRepository.update(this)
            }
        }
    }

}