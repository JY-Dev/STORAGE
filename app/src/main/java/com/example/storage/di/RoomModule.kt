package com.example.storage.di

import com.example.storage.data.Repository
import com.example.storage.data.image.ImageDB
import com.example.storage.data.image.ImageRepositoryImpl
import com.example.storage.data.tag.TagDB
import com.example.storage.data.tag.TagRepositoryImpl
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import org.koin.dsl.module

val roomModule = module {
    single<TagRepositoryImpl> { TagRepositoryImpl(TagDB.Factory.create(get()).getDao())}
    single<ImageRepositoryImpl> { ImageRepositoryImpl(ImageDB.Factory.create(get()).getDao()) }
}