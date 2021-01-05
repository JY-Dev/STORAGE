package com.example.storage.ui.detailsearch

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivityDetailSearchBinding
import com.example.storage.model.ImageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailSearchActivity : BaseActivity() , DetailSearchContract.View {
    val bind by binding<ActivityDetailSearchBinding>(R.layout.activity_detail_search)
    var toggle = true
    lateinit var mCheckBox: CheckBox
    lateinit var presenter: DetailSearchPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.apply {
            presenter = DetailSearchPresenter()
            intent.getParcelableExtra<ImageData>("image")?.run {
                imageData = this
            }
            mCheckBox = storyCheckbox
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

    fun storyUpdate(view: View , imageData: ImageData){
        val checkBox = view as CheckBox
        CoroutineScope(Dispatchers.IO).launch {
            presenter.storyUpdate(imageData,checkBox.isChecked)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pageAnimation()
    }

}