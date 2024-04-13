package com.griffin.example.engine

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.griffin.example.R
import com.griffin.kp.extend.engine.KPictureImageEngine


class GlideEngine : KPictureImageEngine {

    override fun loadImage(
        context: Context,
        url: String,
        targetView: ImageView,
        width: Int,
        height: Int
    ) {
        Glide.with(context)
            .load(url)
            .override(width, height)
            .centerCrop()
            .placeholder(R.drawable.ic_file_place)
            .into(targetView)
    }

    override fun loadAlbumImage(
        context: Context,
        url: String,
        targetView: ImageView,
        width: Int,
        height: Int
    ) {
        Glide.with(context)
            .load(url)
            .override(width, height)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(targetView)
    }

}