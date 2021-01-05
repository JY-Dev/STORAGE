package com.example.storage.ui.detailsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.databinding.ActivityDetailSearchBinding
import com.example.storage.model.ImageData
import com.nex3z.flowlayout.FlowLayout
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
                setTag(flowView,this)
            }
            mCheckBox = storyCheckbox
            activity = this@DetailSearchActivity
            root.setOnClickListener {
                //blerView.visibility = if(!toggle) View.VISIBLE else View.GONE
                backBtn.visibility = if(!toggle) View.VISIBLE else View.GONE
                moreBtn.visibility = if(!toggle) View.VISIBLE else View.GONE
                storyCheckbox.visibility = if(!toggle) View.VISIBLE else View.GONE
                titleTv.visibility = if(!toggle) View.VISIBLE else View.GONE
                flowView.visibility = if(!toggle) View.VISIBLE else View.GONE
                if(!toggle) YoYo.with(Techniques.FadeIn)
                    .duration(500)
                    .playOn(blerView)
                else YoYo.with(Techniques.FadeOut)
                    .duration(500)
                    .playOn(blerView)
                toggle = !toggle

            }
        }
    }

    private fun toggleUI(){
        if(toggle) hideSystemUI()
        else showSystemUI()
        toggle = !toggle
    }

    private fun setTag(view: FlowLayout,imageData : ImageData){
        imageData.tags.forEach {
            val tag = it
            LayoutInflater.from(this).inflate(R.layout.item_detail_tag,null).apply {
                val inner = this
                findViewById<TextView>(R.id.tag_tv).apply {
                    text = it
                    id = View.generateViewId()
                }
                findViewById<ImageButton>(R.id.close_btn).apply {
                    setOnClickListener {
                        view.removeView(inner)
                        CoroutineScope(Dispatchers.IO).launch {
                            presenter.deleteTag(imageData,tag)
                        }
                    }
                    id = View.generateViewId()
                }
                view.addView(this)
            }
        }

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