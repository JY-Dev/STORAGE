package com.example.storage.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.databinding.ItemMainTagBinding
import com.example.storage.databinding.ItemSearchFilterBinding
import com.example.storage.model.ImageData
import com.example.storage.model.TagData

class SearchFilterAdapter(val deleteFilter:(tagData : TagData) -> Unit) : RecyclerView.Adapter<SearchFilterAdapter.ViewHolder>() {
    var tagList = mutableListOf<String>()
    inner class ViewHolder(private val binding: ItemSearchFilterBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag : String){
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_filter, parent, false),parent.context)

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tagList[position])
    }


}