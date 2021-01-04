package com.example.storage.ui.detailsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivityDetailSearchBinding
import com.example.storage.databinding.ActivityDetailSearchBindingImpl

class DetailSearchActivity : BaseActivity() {
    val bind by binding<ActivityDetailSearchBinding>(R.layout.activity_detail_search)
    var toggle = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.apply {
            intent.getStringExtra("imgUri")?.run {
                imageUrl = this
            }
            activity = this@DetailSearchActivity
            root.setOnClickListener {
                blerView.visibility = if(!toggle) View.VISIBLE else View.GONE
                backBtn.visibility = if(!toggle) View.VISIBLE else View.GONE
                moreBtn.visibility = if(!toggle) View.VISIBLE else View.GONE
                storyCheckbox.visibility = if(!toggle) View.VISIBLE else View.GONE
                toggleUI()
            }
        }

    }

    private fun toggleUI(){
        if(toggle) hideSystemUI()
        else showSystemUI()
        toggle = !toggle
    }

    override fun onDestroy() {
        super.onDestroy()
        pageAnimation()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pageAnimation()
    }
}