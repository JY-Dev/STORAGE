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
import com.example.storage.util.getImageDateFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : BaseActivity(), MainContract.View {
    val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    lateinit var presenter: MainPresenter
    lateinit var mStoryAdapter: StoryAdapter
    lateinit var mTagAdapter: MainTagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this)
        binding.apply {
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            storyItemDecoration = StoryItemDecoration()
            mainItemDecoration = MainItemDecoration()
            storyAdapter = StoryAdapter(
                gotoImageDetail = {
                    startActivity<DetailActivity>(this@MainActivity,"imgUri",it)
                }).apply {
                presenter.getImageData()
                mStoryAdapter = this
            }
            tagAdapter = MainTagAdapter(
                update = { tagData -> presenter.tagUpdate(tagData)
                refreshOnResume()},
                searchTag = { tag ->
                    startActivity<SearchActivity>(this@MainActivity,"tag",tag)
                }).apply {
                mTagAdapter = this
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshOnResume()
    }

    private fun refreshOnResume() {
        presenter.getImageData()
    }

    fun gotoSearch() {
        startActivity<SearchActivity>(this)
    }

    override fun setImage(images: MutableList<ImageData>) {
        mTagAdapter.apply {
            imageList = images
        }
    }

    override fun setStory(images: MutableList<ImageData>) {
        mStoryAdapter.apply {
            storyList = images
            notifyDataSetChanged()
        }
    }

    override fun setTag(tags: MutableList<TagData>) {
        mTagAdapter.apply {
            tagList = sortedTag(tags)
            notifyDataSetChanged()
        }
    }

    private fun sortedTag(tags: MutableList<TagData>): MutableList<TagData> {
        return mutableListOf<TagData>().apply {
            addAll(tags.filter { it.favorites }.sortedBy { it.count })
            addAll(tags.filter { !it.favorites }.sortedBy { it.count })
        }
    }

}