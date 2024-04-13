package com.griffin.kp.def

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.griffin.kp.KPicture
import com.griffin.kp.R
import com.griffin.kp.adapter.MediaFolderPopupAdapter
import com.griffin.kp.model.AlbumMediaFolder

/**
 * 默认媒体目录ViewHolder
 */
class MediaFolderViewHolder(view: View) : MediaFolderPopupAdapter.BaseViewHolder(view) {

    private val imageEngine = KPicture.imageEngine
    private val ivCover = view.findViewById<ImageView>(R.id.kp_iv_album_cover)
    private val tvTitle = view.findViewById<TextView>(R.id.kp_tv_album_title)

    override fun onBind(item: AlbumMediaFolder) {
        ivCover.post {
            imageEngine?.loadAlbumImage(
                ivCover.context,
                item.coverPath,
                ivCover,
                ivCover.width,
                ivCover.height
            )
        }
        tvTitle?.text = "${item.folderName}（${item.total}）"
    }

}