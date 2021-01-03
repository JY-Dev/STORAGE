package com.example.storage.base

import com.example.storage.data.Repository
import com.example.storage.data.image.ImageRepositoryImpl
import com.example.storage.data.tag.TagRepositoryImpl
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject

abstract class BasePresenter : KoinComponent {
    val imageRepository: ImageRepositoryImpl by inject()
    val tagRepository: TagRepositoryImpl by inject()

    fun runBackground(func : () -> Unit) {
        Runnable {
            func()
        }.apply { Thread(this).start() }
    }
}