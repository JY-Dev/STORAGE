package com.example.storage.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivityDetailBinding
import com.example.storage.ui.detailsearch.DetailSearchActivity


class DetailActivity : BaseActivity() {
    companion object{
        const val DURATION = 300L
    }
    val binding by binding<ActivityDetailBinding>(R.layout.activity_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            this@DetailActivity.run {
                activity = this
                lifecycleOwner = this
                toggle.observe(this, Observer {
                    when(it){
                        true -> blerView.fadeInUI(DURATION)
                        else -> blerView.fadeOutUI(DURATION)
                    }
                })
            }
            imageUrl = intent.getStringExtra("imgUri")
            root.setOnClickListener {
                toggle.value = !toggle.value!!
            }
        }
    }

}