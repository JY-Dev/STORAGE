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
import com.example.storage.util.dp

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
        .transform(CenterCrop(),RoundedCorners(25))
        .into(view)
}


@BindingAdapter("bind:adapter")
fun setAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    view.adapter = baseAdapter
}

@BindingAdapter("bind:dataObserverAdapter")
fun setDataObserverAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>){
    view.adapter = adapter.apply {
        registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                view.smoothScrollToPosition(adapter.itemCount)
            }
        })
    }
}

@BindingAdapter("bind:itemDecoration")
fun setItemDecoration(view : RecyclerView, itemDecoration: RecyclerView.ItemDecoration){
    view.addItemDecoration(itemDecoration)
}

@BindingAdapter("bind:setVisibility")
fun setVisibility(view : View, toggle: Boolean){
    view.visibility = if (toggle) View.VISIBLE else View.GONE
}