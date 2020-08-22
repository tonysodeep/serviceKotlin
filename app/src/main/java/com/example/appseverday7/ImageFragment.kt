package com.example.appseverday7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ImageFragment : Fragment() {
    lateinit var cursor: Cursor
    var gridView : GridView? = null
    private var gridViewLayoutManager: GridLayoutManager? = null
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        gridView = rootView.findViewById<GridView>(R.id.gridview)
        val rootView = inflater.inflate(R.layout.fragment_image,container,false)
        recyclerView = rootView.findViewById(R.id.rv_gridview)
        gridViewLayoutManager = GridLayoutManager(activity,3, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = gridViewLayoutManager
        recyclerView.setHasFixedSize(true)
        var listString = ArrayList<String>()
        recyclerView?.adapter = ImageAdapter(listString)
        val broadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var arrayString: ArrayList<String>? = intent?.getStringArrayListExtra("data")
                (recyclerView.adapter as ImageAdapter).setData(arrayString)
            }
        }
        activity?.let { LocalBroadcastManager.getInstance(it).registerReceiver(
            broadcastReceiver, IntentFilter("LoadImageService")
        ) }

        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
            != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    Array(1) { android.Manifest.permission.READ_EXTERNAL_STORAGE },
                    121
                )
            }

        } else {
            val intent = Intent(activity,LoadingImageService::class.java)
            activity?.startService(intent)
//            listImage()
        }
        return rootView
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 121 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(activity,LoadingImageService::class.java)
            activity?.startService(intent)
//            listImage()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.stopService(activity!!.intent)
    }

}