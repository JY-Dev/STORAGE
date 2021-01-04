package com.example.storage.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity() {
    val binding by binding<ActivityDetailBinding>(R.layout.activity_detail)
    var toggle = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            imageUrl = intent.getStringExtra("imgUri")
            root.setOnClickListener {
                blerView.visibility = if(!toggle) View.VISIBLE else View.GONE
                image.visibility = if(!toggle) View.VISIBLE else View.GONE
                moreBtn.visibility = if(!toggle) View.VISIBLE else View.GONE
                imageBack.visibility = if(!toggle) View.VISIBLE else View.GONE
                toggleUI()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fadein,R.anim.fadeout)
    }

    private fun toggleUI(){
        if(toggle) hideSystemUI()
        else showSystemUI()
        toggle = !toggle
    }


}