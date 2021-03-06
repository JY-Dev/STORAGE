package com.example.storage.base

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.storage.R

abstract class BaseActivity : AppCompatActivity() {
    /**
     * DataBinding
     */
    protected inline fun <reified T : ViewDataBinding> binding(resId: Int) : Lazy<T> =
        lazy{ DataBindingUtil.setContentView<T>(this,resId)}

    /**
     * 토스트
     */
    fun String.showLongToast() = Toast.makeText(applicationContext,this,Toast.LENGTH_LONG).show()
    fun String.showShortToast() = Toast.makeText(applicationContext,this,Toast.LENGTH_SHORT).show()

    /**
     * Start Activity
     */
    protected inline fun <reified T : BaseActivity> startActivity(activity : Activity){
        startActivity(Intent(activity,T::class.java))
        pageAnimation()
    }

    protected inline fun <reified T : BaseActivity> startActivity(activity : Activity, delay : Long){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(activity,T::class.java))
            finish()
        },delay)
    }

    protected inline fun <reified T : BaseActivity> startActivity(activity : Activity, key : String , value : String){
        startActivity(Intent(activity,T::class.java).apply {
            putExtra(key,value)
        })
        pageAnimation()
    }

    fun View.fadeInUI(duration: Long) =
        YoYo.with(Techniques.FadeIn)
            .duration(duration)
            .playOn(this)

    fun View.fadeOutUI(duration: Long) =
        YoYo.with(Techniques.FadeOut)
            .duration(duration)
            .playOn(this)

    var toggle = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun pageAnimation(){
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    fun Activity.hideKeyBoard(){
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        pageAnimation()
    }
}