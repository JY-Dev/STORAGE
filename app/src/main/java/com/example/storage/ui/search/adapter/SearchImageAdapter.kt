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
import com.example.storage.databinding.ItemSearchImageBinding
import com.example.storage.model.ImageData
import com.example.storage.model.TagData

class SearchImageAdapter(val images : MutableList<ImageData>,val detail:(image : String) -> Unit) : RecyclerView.Adapter<SearchImageAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemSearchImageBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageData : ImageData){
            binding.apply {
                imageUri = imageData.imageUri
                root.setOnClickListener {
                    detail(imageData.imageUri)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_image, parent, false),parent.context)

    override fun getItemCount(): Int =
        images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(images[position])

}