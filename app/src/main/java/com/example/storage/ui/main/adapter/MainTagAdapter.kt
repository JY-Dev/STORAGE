package com.example.storage.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.databinding.ItemMainTagBinding
import com.example.storage.model.ImageData
import com.example.storage.model.TagData

class MainTagAdapter(val update:(tagData : TagData) -> Unit, val searchTag : (tag : String) -> Unit) : RecyclerView.Adapter<MainTagAdapter.ViewHolder>() {
    var imageList = mutableListOf<ImageData>()
    var tagList = mutableListOf<TagData>()
    inner class ViewHolder(private val binding: ItemMainTagBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tagItem : TagData, uri : String){
            binding.apply {
                tagData = tagItem
                adapter = this@MainTagAdapter
                imageUri = uri
                tag = tagItem.tag
                isCheck = tagItem.favorites
                root.setOnClickListener {
                    searchTag(tagItem.tag)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_main_tag, parent, false),parent.context)

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tagList[position],imageList.filter { it.tags.contains(tagList[position].tag) }[0].imageUri)
    }

    fun updateTag(tagData: TagData,view: View) = update(tagData.apply {
        val checkBox = view as CheckBox
        favorites = checkBox.isChecked
    })

}