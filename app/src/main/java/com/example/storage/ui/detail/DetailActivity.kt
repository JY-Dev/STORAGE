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

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}