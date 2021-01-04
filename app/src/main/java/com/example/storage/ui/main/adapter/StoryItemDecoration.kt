package com.example.storage.ui.main.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.util.dp


class StoryItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if(itemPosition>=0)
            outRect.set(0,0,10.dp,0)
    }
}