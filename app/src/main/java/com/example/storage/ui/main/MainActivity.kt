package com.example.storage.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.storage.R
import com.example.storage.databinding.ActivityMainBinding
import com.example.storage.base.BaseActivity
import com.example.storage.model.ImageData
import com.example.storage.ui.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() , MainContract.View {
    val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    lateinit var presenter: MainPresenter
    lateinit var storyAdapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(this)

        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = StoryAdapter {
                val intent = Intent(this@MainActivity,DetailActivity::class.java).apply {
                    putExtra("imgUri",it)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.fadein,R.anim.fadeout)
            }.apply {
                CoroutineScope(Dispatchers.IO).launch{
                    presenter.getStoryData()
                }
                storyAdapter = this
            }
        }

    }

    override suspend fun setStory(storyList: MutableList<ImageData>) {
            withContext(Dispatchers.Main){
                storyAdapter.apply {
                    this.storyList = storyList
                    notifyDataSetChanged()
                }
            }
    }
}