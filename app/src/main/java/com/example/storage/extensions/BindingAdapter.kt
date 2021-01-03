package com.example.storage.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("bind:setImage")
fun setImage(view : ImageView , url : String){
    Glide.with(view)
        .load(url)
        .centerCrop()
        .into(view)
}