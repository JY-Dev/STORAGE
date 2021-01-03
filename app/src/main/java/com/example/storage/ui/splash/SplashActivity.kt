package com.example.storage.ui.splash

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.storage.R
import com.example.storage.base.BaseActivity
import com.example.storage.ui.main.MainContract
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class SplashActivity : BaseActivity() , SplashContract.View {
    lateinit var presenter: SplashPresenter
    val composit = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this)
        requestPermission {
            loadImage()
        }
    }

    private fun requestPermission(func : () -> Unit){
        val permissionListener = object : PermissionListener{
            override fun onPermissionGranted() {
                func()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                "PermissionDenied".showShortToast()
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }
    /**
     * LoadImage
     */
    private fun loadImage(){
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )
        val selection = "${MediaStore.Images.Media.BUCKET_DISPLAY_NAME} ==?"
        val selectionArgs = arrayOf("Screenshots")

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val displayName = cursor.getString(displayNameColumn)
                val album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val contentUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
                val date = Date((cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))*1000L))
                //presenter.imageDataCheck(contentUri.toString(),date.time)
                CoroutineScope(Dispatchers.IO).launch {
                    presenter.test(contentUri.toString())
                }
                //Thread.sleep(500)
                Log.d("MainActivity", "id: $id, display_name: $displayName, date_taken: ${date.time} content_uri: $contentUri ablum:$album")
            }
            //startActivity<MainActivity>(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        composit.dispose()
    }

    private fun Date.getDateFormat() : String = SimpleDateFormat("yyyy-MM-dd",Locale.KOREA).format(this)
    override fun getCompositeDisposable(): CompositeDisposable = composit
}