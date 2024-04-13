package com.griffin.kp.def

import android.view.View
import android.widget.ImageView
import com.griffin.kp.KPicture
import com.griffin.kp.R
import com.griffin.kp.adapter.MediaFileAdapter
import com.griffin.kp.model.AlbumMediaFile

/**
 * 默认媒体ViewHolder
 */
class MediaViewHolder(view: View) : MediaFileAdapter.BaseViewHolder(view) {

    private val imageEngine = KPicture.imageEngine
    private val ivPicture = view.findViewById<ImageView>(R.id.iv_picture)

    override fun onBind(item: AlbumMediaFile) {
        itemView.post {
            val layoutParams = itemView.layoutParams
            layoutParams.height = itemView.measuredWidth
            itemView.layoutParams = layoutParams
            imageEngine?.loadImage(
                ivPicture.context,
                item.path,
                ivPicture,
                ivPicture.width,
                ivPicture.height
            )
        }
    }

}