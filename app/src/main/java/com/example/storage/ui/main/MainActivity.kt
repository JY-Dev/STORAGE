package com.example.storage.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.storage.R
import com.example.storage.databinding.ActivityMainBinding
import com.example.storage.base.BaseActivity
import com.example.storage.model.ImageData
import com.example.storage.model.TagData
import com.example.storage.ui.detail.DetailActivity
import com.example.storage.ui.main.adapter.MainItemDecoration
import com.example.storage.ui.main.adapter.MainTagAdapter
import com.example.storage.ui.main.adapter.StoryAdapter
import com.example.storage.ui.main.adapter.StoryItemDecoration
import com.example.storage.ui.search.SearchActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() , MainContract.View {
    val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    lateinit var presenter: MainPresenter
    lateinit var mStoryAdapter: StoryAdapter
    lateinit var mTagAdapter: MainTagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this)

        binding.apply {
            lifecycleOwner = this@MainActivity
            storyItemDecoration = StoryItemDecoration()
            mainItemDecoration = MainItemDecoration()
            storyAdapter = StoryAdapter (
                gotoImageDetail = {
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("imgUri", it)
                })
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }).apply {
                CoroutineScope(Dispatchers.IO).launch{
                    presenter.getImageData()
                }
                mStoryAdapter = this
            }
            tagAdapter = MainTagAdapter(
                update =  { tagData ->
                CoroutineScope(Dispatchers.IO).launch {
                    presenter.tagUpdate(tagData)
                    presenter.getTagData()
                }
            }, searchTag = { tag ->
                startActivity(Intent(this@MainActivity,SearchActivity::class.java).apply {
                    putExtra("tag",tag)
                })
            }).apply {
                CoroutineScope(Dispatchers.IO).launch {
                    presenter.getTagData()
                }
                mTagAdapter = this
            }
        }

    }

    override suspend fun setImage(images: MutableList<ImageData>) {
        withContext(Dispatchers.Main){
            mTagAdapter.apply {
                imageList = images
            }
        }
    }

    override suspend fun setStory(images: MutableList<ImageData>) {
            withContext(Dispatchers.Main){
                mStoryAdapter.storyList = images
            }
    }

    override suspend fun setTag(tags: MutableList<TagData>) {
        withContext(Dispatchers.Main){
            mTagAdapter.apply {
                tagList = sortedTag(tags)
                notifyDataSetChanged()
            }
        }
    }

    private fun sortedTag(tags: MutableList<TagData>) : MutableList<TagData>{
        return mutableListOf<TagData>().apply {
            addAll(tags.filter { it.favorites }.sortedBy { it.count })
            addAll(tags.filter { !it.favorites }.sortedBy { it.count })
        }
    }

}