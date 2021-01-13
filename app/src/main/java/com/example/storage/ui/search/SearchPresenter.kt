package com.example.storage.ui.search

import com.example.storage.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(val mView : SearchContract.View) : SearchContract.Presenter , BasePresenter() {
    override fun getImageData() {
        CoroutineScope(Dispatchers.Main).launch{
            mView.refreshImage(getImage())
        }
    }

    private suspend fun getImage() = withContext(Dispatchers.IO){
        imageRepository.getData()
    }

}