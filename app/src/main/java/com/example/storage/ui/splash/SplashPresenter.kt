package com.example.storage.ui.splash

import com.example.storage.base.BasePresenter
import com.example.storage.model.TagData
import kotlinx.coroutines.*
import kotlin.random.Random

class SplashPresenter(val mView: SplashContract.View) : SplashContract.Presenter, BasePresenter() {
    val tagMock = mutableListOf(
        mutableListOf("test", "ok", "check", "bird"),
        mutableListOf("bird", "ok", "test", "seoul"),
        mutableListOf("now", "time"),
        mutableListOf("dog", "animal", "bird", "ok"),
        mutableListOf("me", "test", "seoul")
    )

    /*    suspend fun test(imageUri: String){
            tagMock[Random.nextInt(tagMock.size)].apply{
                println("insert url$imageUri")
                //imageRepository.insert(ImageData(imageUri, false, this, date))
                forEach {
                    println("tag="+it)
                    updateTagCount(it)
                }
            }
        }*/
    suspend fun test(imageUri: String) {
        tagMock[Random.nextInt(tagMock.size)].apply {
            updateTagCount(this)
        }
    }

    private suspend fun updateTagCount(tags: MutableList<String>) {
        tags.forEach { tag ->
            if (tagRepository.getDataFromKey(tag as String) == null) insertTagdata(tag)
            tagRepository.getDataFromKey(tag as String)?.run {
                count += 1
                println("tag=$tag count=$count")
                updateTagData(this)
            }
        }
    }

    override fun imageDataCheck(imageUri: String, date: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            tagMock[Random.nextInt(tagMock.size)].apply {
                updateTagCount(this)
            }
        }
        /*runBackground {
            tagMock[Random.nextInt(tagMock.size)].apply{
                println("insert url$imageUri")
                //imageRepository.insert(ImageData(imageUri, false, this, date))
                forEach {
                    println("tag="+it)
                    updateTagCount(it)
                }
            }
        }*/
        /*  val observable = io.reactivex.Flowable.just(imageUri)
              .subscribeOn(Schedulers.io())
              .observeOn(Schedulers.newThread())
              .subscribe { uri ->
                  if (imageRepository.getData().none { it.imageUri == uri }){
                      val tags = tagMock[Random.nextInt(tagMock.size)].apply {
                          val observable = io.reactivex.Flowable.fromIterable(this)
                              .subscribeOn(Schedulers.io())
                              .observeOn(Schedulers.newThread())
                              .doOnComplete {
                                  println("insert url$uri")
                                  imageRepository.insert(ImageData(uri, false, this, date))
                              }
                              .subscribe {
                                  println("tag="+it)
                                  updateTagCount(it)
                              }
                      }
                  } else {
                      println("remain data=$imageUri")
                  }
              }*/

    }




    private suspend fun insertTagdata(tag: String) {
        tagRepository.insert(TagData(tag, 1, false))
    }


    private suspend fun updateTagData(data: TagData) =
        tagRepository.update(data)
}