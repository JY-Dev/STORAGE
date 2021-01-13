package com.example.storage.ui.detailsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
    companion object{
        const val DURATION = 300L
    }
    val bind by binding<ActivityDetailSearchBinding>(R.layout.activity_detail_search)

    lateinit var presenter: DetailSearchPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.apply {
            presenter = DetailSearchPresenter()
            intent.getParcelableExtra<ImageData>("image")?.run {
                imageData = this
                setTag(flowView,this)
            }
            this@DetailSearchActivity.run {
                activity = this
                lifecycleOwner = this
                blerView.setToggle(this)
            }
            root.setOnClickListener {
                setToggle()
            }
        }
    }

    private fun View.setToggle(lifecycleOwner: LifecycleOwner){
        toggle.observe(lifecycleOwner, Observer {
            when(it){
                true -> fadeInUI(DURATION)
                else -> fadeOutUI(DURATION)
            }
        })
    }

    private fun setToggle(){
        toggle.value = !toggle.value!!
    }

    private fun setTag(view: FlowLayout,imageData : ImageData){
        imageData.tags.forEach {
            val tag = it
            LayoutInflater.from(this).inflate(R.layout.item_detail_tag,null,false).apply {
                val itemView = this
                findViewById<TextView>(R.id.tag_tv).setTagTv(tag)
                findViewById<ImageButton>(R.id.close_btn).setDeleteListener {
                    view.removeView(itemView)
                    presenter.deleteTag(imageData,tag)
                }
                view.addView(itemView)
            }
        }
    }

    private fun TextView.setTagTv(tag : String) {
        text = tag
        id = View.generateViewId()
    }

    private fun View.setDeleteListener(delete : () -> Unit){
        setOnClickListener {
            delete()
        }
        id = View.generateViewId()
    }

    fun storyUpdate(view: View , imageData: ImageData){
        val checkBox = view as CheckBox
        presenter.storyUpdate(imageData,checkBox.isChecked)
    }

}