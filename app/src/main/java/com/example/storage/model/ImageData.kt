package com.example.storage.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ImageData(@PrimaryKey var imageUri: String,
                     var story : Boolean,
                     var tags : MutableList<String>,
                     var date : Long) : ModelData ,Parcelable