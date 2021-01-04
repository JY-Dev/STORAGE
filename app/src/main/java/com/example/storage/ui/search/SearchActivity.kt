package com.example.storage.ui.search

import android.os.Bundle
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {
    val bind by binding<ActivitySearchBinding>(R.layout.activity_search)
    val filterList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.apply {
            lifecycleOwner = this@SearchActivity
            getFilterForInent()
        }
    }

    private fun getFilterForInent(){
        intent.getStringExtra("tag")?.run {
            setFilter(this , Filter.ADD)
        }
    }

    private fun setFilter(tag : String , filter : Filter){
        filterList.apply {
            when(filter){
                is Filter.ADD -> add(tag)
                is Filter.REMOVE -> remove(tag)
            }
        }
    }

    sealed class Filter {
        object ADD : Filter()
        object REMOVE : Filter()
    }
}

