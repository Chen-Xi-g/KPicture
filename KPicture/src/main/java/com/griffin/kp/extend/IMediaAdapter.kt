package com.griffin.kp.extend

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.KPicture
import com.griffin.kp.adapter.MediaFileAdapter

/**
 * 自定义图片列表接口
 *
 * 通过回调可以注入自定义相册列表。
 */
interface IMediaAdapter {

    /**
     * 设置LayoutManager
     *
     * @return RecyclerView.LayoutManager 默认返回[GridLayoutManager] 4列
     */
    fun getLayoutManager(context: Context): RecyclerView.LayoutManager{
        return GridLayoutManager(context, KPicture.theme?.albumColumn ?: 4)
    }

    /**
     * 注入布局
     *
     * Adapter中的onCreateViewHolder回调
     *
     * @return 返回继承[MediaFileAdapter.BaseViewHolder]的自定义ViewHolder
     */
    fun onCreateViewHolder(parent: ViewGroup): MediaFileAdapter.BaseViewHolder

}