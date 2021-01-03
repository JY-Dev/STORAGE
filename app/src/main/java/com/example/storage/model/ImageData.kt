package com.example.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageData(@PrimaryKey val imageUri: String,
                     val story : Boolean,
                     val tags : MutableList<String>,
                     val date : Long) : ModelData