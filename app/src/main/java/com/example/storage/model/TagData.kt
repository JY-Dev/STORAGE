package com.example.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.storage.model.ModelData

@Entity
data class TagData(@PrimaryKey val tag: String,
                   var count : Int,
                   var favorites : Boolean) : ModelData