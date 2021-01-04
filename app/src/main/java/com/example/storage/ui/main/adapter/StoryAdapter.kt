package com.example.storage.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.databinding.ItemStoryBinding
import com.example.storage.model.ImageData

class StoryAdapter(val gotoImageDetail:(imageUri : String) -> Unit) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    var storyList = mutableListOf<ImageData>()
    inner class ViewHolder(private val binding: ItemStoryBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ImageData){
            binding.apply {
                imgUrl = item.imageUri
                root.setOnClickListener {
                    gotoImageDetail(item.imageUri)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_story, parent, false),parent.context)

    override fun getItemCount(): Int = storyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storyList[position])
    }
}