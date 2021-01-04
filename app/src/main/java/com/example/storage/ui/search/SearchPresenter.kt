package com.example.storage.ui.search

import com.example.storage.base.BasePresenter

class SearchPresenter(val mView : SearchContract.View) : SearchContract.Presenter , BasePresenter() {
    override suspend fun getImageData() {
        mView.refreshImage(imageRepository.getData())
    }

}