package com.example.storage.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivitySearchBinding
import com.example.storage.model.ImageData
import com.example.storage.ui.detail.DetailActivity
import com.example.storage.ui.detailsearch.DetailSearchActivity
import com.example.storage.ui.search.adapter.FilterItemDecoration
import com.example.storage.ui.search.adapter.SearchFilterAdapter
import com.example.storage.ui.search.adapter.SearchImageAdapter
import com.example.storage.ui.search.adapter.SearchImageItemDecoration
import com.example.storage.util.getImageDateFormat
import kotlinx.android.synthetic.main.item_story.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

class SearchActivity : BaseActivity(), SearchContract.View {
    val bind by binding<ActivitySearchBinding>(R.layout.activity_search)
    lateinit var searchPresenter: SearchPresenter
    val filters = mutableListOf<String>()
    val searchText = MutableLiveData<String>()
    lateinit var mGroupView: LinearLayout
    lateinit var searchFilterAdapter: SearchFilterAdapter
    lateinit var itemView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchPresenter = SearchPresenter(this)
        itemView =
            LayoutInflater.from(this@SearchActivity).inflate(R.layout.item_image, null, false)
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
            mGroupView = groupView

            getFilterForIntent()
        }
    }

    private fun getFilterForIntent() {
        val tag = intent.getStringExtra("tag") ?: ""
        tag.run {
            if (this == "") getImage()
            else setFilter(this, Filter.ADD)
        }
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
                is Filter.ADD -> if (!this.contains(tag)) add(tag)
                is Filter.REMOVE -> remove(tag)
            }
            searchFilterAdapter.apply {
                tagList = filters
                notifyDataSetChanged()
                getImage()
            }
        }
    }

    sealed class Filter {
        object ADD : Filter()
        object REMOVE : Filter()
    }

    override fun onResume() {
        super.onResume()
        getImage()
    }

    private fun getImage() {
        searchPresenter.getImageData()
    }

    override fun refreshImage(images: MutableList<ImageData>) {
        mGroupView.removeAllViews()
        imageSort(images)
    }

    private fun imageSort(images: MutableList<ImageData>) {
        sortedImageFromDate(checkContainTags(images)).forEach { (date, list) ->
            LayoutInflater.from(this@SearchActivity).inflate(R.layout.item_image, null, false)
                .itemViewSet(date, list)
        }
    }

    private fun View.itemViewSet(date: String, list: MutableList<ImageData>) {
        this.findViewById<TextView>(R.id.date_tv).setDate(date)
        this.findViewById<RecyclerView>(R.id.image_list).setImageList(list)
        mGroupView.addView(this)
    }

    private fun TextView.setDate(date: String) = this.run {
        text = date
        id = View.generateViewId()
    }

    private fun RecyclerView.setImageList(images: MutableList<ImageData>) = this.run {
        adapter = SearchImageAdapter(images) { image ->
            startActivity(Intent(this@SearchActivity, DetailSearchActivity::class.java).apply {
                putExtra("image", image)
            })
            pageAnimation()
        }
        addItemDecoration(SearchImageItemDecoration())
        id = View.generateViewId()
    }

    private fun checkContainTags(images: MutableList<ImageData>): MutableList<ImageData> =
        images.filter { test -> filters.all { data -> test.tags.contains(data) } }.toMutableList()

    private fun sortedImageFromDate(images: MutableList<ImageData>): LinkedHashMap<String, MutableList<ImageData>> {
        return linkedMapOf<String, MutableList<ImageData>>().apply {
            images.forEach {
                Date(it.date).getImageDateFormat().apply {
                    when (true) {
                        containsKey(this) -> get(this)?.add(it)
                        else -> set(this, mutableListOf(it))
                    }
                }
            }
        }
    }
}

