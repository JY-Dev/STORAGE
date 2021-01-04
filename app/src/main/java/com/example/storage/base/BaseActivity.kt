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
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    protected inline fun <reified T : BaseActivity> startActivity(activity : Activity, delay : Long){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(activity,T::class.java))
            finish()
        },delay)
    }



    fun hideSystemUI() {
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
    fun showSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    fun pageAnimation(){
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    fun Activity.hideKeyBoard(){
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }
}