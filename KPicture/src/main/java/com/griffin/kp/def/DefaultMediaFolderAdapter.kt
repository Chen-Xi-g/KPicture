package com.griffin.kp.def

import android.view.LayoutInflater
import android.view.ViewGroup
import com.griffin.kp.R
import com.griffin.kp.adapter.MediaFolderPopupAdapter
import com.griffin.kp.extend.IMediaFolderAdapter

class DefaultMediaFolderAdapter : IMediaFolderAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): MediaFolderPopupAdapter.BaseViewHolder {
        return MediaFolderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.kp_item_popup, parent, false))
    }
}