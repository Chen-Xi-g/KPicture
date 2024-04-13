package com.griffin.kp.def

import android.view.LayoutInflater
import android.view.ViewGroup
import com.griffin.kp.R
import com.griffin.kp.adapter.MediaFileAdapter
import com.griffin.kp.extend.IMediaAdapter

/**
 * 默认相册列表
 */
class DefaultMediaAdapter : IMediaAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): MediaFileAdapter.BaseViewHolder {
        return MediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.kp_item_picture_layout, null, false))
    }

}