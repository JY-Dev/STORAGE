package com.example.storage.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bind:setImage")
fun setImage(view : ImageView , url : String){
    Glide.with(view)
        .load(url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("bind:setRoundImage")
fun setRoundImage(view : ImageView , url : String){
    Glide.with(view)
        .load(url)
        .centerCrop()
        .into(view)
}


@BindingAdapter("bind:adapter")
fun setAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    view.adapter = baseAdapter
}

@BindingAdapter("bind:itemDecoration")
fun setItemDecoration(view : RecyclerView, itemDecoration: RecyclerView.ItemDecoration){
    view.addItemDecoration(itemDecoration)
}

@BindingAdapter("bind:setVisibility")
fun setVisibility(view : View, toggle: MutableLiveData<Boolean>){
    println("test="+toggle.value)
}