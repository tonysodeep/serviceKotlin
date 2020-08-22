package com.example.appseverday7

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.grildlayout_image.view.*

class ImageAdapter(var imageList: ArrayList<String>) :
    RecyclerView.Adapter<ImageAdapter.itemViewHolder>() {
    class itemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewItem: ImageView = itemView.image_gallary
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.itemViewHolder {
        var imageGalley =
            LayoutInflater.from(parent.context).inflate(R.layout.grildlayout_image, parent, false)
        return itemViewHolder(imageGalley)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val filePath = imageList[position]
        Glide.with(holder.itemView.context)
            .load(filePath)
            .into(holder.imageViewItem)
    }
    fun setData(array: ArrayList<String>?){
        array?.let {
            imageList = it
            notifyDataSetChanged()}
    }
}
