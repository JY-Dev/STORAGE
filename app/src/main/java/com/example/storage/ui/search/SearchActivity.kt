package com.example.storage.ui.search

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivitySearchBinding
import com.example.storage.model.ImageData
import com.example.storage.ui.search.adapter.FilterItemDecoration
import com.example.storage.ui.search.adapter.SearchFilterAdapter
import com.example.storage.util.getImageDateFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

class SearchActivity : BaseActivity(), SearchContract.View {
    val bind by binding<ActivitySearchBinding>(R.layout.activity_search)
    lateinit var searchPresenter: SearchPresenter
    val filters = mutableListOf<String>()
    val searchText = MutableLiveData<String>()
    lateinit var searchFilterAdapter: SearchFilterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPresenter = SearchPresenter(this)
        getImage()
        bind.apply {
            lifecycleOwner = this@SearchActivity
            searchAdapter = SearchFilterAdapter(delete = { tag ->
                setFilter(tag, Filter.REMOVE)
            }).apply {
                searchFilterAdapter = this
            }
            filterItemDecoration = FilterItemDecoration()
            searchTv = searchText
            activity = this@SearchActivity
            getFilterForIntent()
        }
    }

    private fun getFilterForIntent() {
        intent.getStringExtra("tag")?.run {
            setFilter(this, Filter.ADD)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pageAnimation()
    }

    fun search() {
        searchText.value?.run {
            if (trim() != "") {
                setFilter(this, Filter.ADD)
                searchText.postValue("")
            }
        }
        hideKeyBoard()
    }

    private fun setFilter(tag: String, filter: Filter) {
        filters.apply {
            when (filter) {
                is Filter.ADD -> if(!this.contains(tag)) add(tag)
                is Filter.REMOVE -> remove(tag)
            }
            searchFilterAdapter.apply {
                tagList = filters
                notifyDataSetChanged()
            }
        }
    }

    sealed class Filter {
        object ADD : Filter()
        object REMOVE : Filter()
    }

    private fun getImage(){
        CoroutineScope(Dispatchers.IO).launch {
            searchPresenter.getImageData()
        }
    }

    override suspend fun refreshImage(images: MutableList<ImageData>) {
        sortedImageFromDate(images).forEach {

        }
    }

    private fun sortedImageFromDate(images: MutableList<ImageData>) : LinkedHashMap<String,MutableList<ImageData>>{
        return linkedMapOf<String,MutableList<ImageData>>().apply {
            images.forEach {
                Date(it.date).getImageDateFormat().apply {
                    when(true){
                        containsKey(this) -> get(this)?.add(it)
                        else -> set(this, mutableListOf(it))
                    }
                }
            }
        }
    }
}

