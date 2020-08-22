package com.example.appseverday7

import android.app.Service
import android.content.Intent
import android.database.Cursor
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView

class LoadingImageService : Service() {
    var recyclerView: RecyclerView? = null
    var arrayList = ArrayList<String>()
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var cols = listOf<String>(MediaStore.Images.Thumbnails.DATA).toTypedArray()
        var cursor = contentResolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            cols, null, null, null
        )!!
        for (i in 0 until cursor?.count!!) {
            cursor.moveToPosition(i)
            var path = cursor.getString(0)
            arrayList.add(path)
        }
        sendBroadcast()
        Log.d("SIZE",arrayList.size.toString())
        return super.onStartCommand(intent, flags, startId)
    }
    fun sendBroadcast (){
        val intent = Intent("LoadImageService")
        intent.putStringArrayListExtra("data",arrayList)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}