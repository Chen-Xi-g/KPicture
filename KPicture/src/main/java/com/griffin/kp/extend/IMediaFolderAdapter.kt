package com.griffin.kp.extend

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.adapter.MediaFileAdapter
import com.griffin.kp.adapter.MediaFolderPopupAdapter

/**
 * 自定义媒体目录
 *
 * 通过回调可以注入自定义媒体目录。
 */
interface IMediaFolderAdapter {

    /**
     * 设置LayoutManager
     *
     * @return RecyclerView.LayoutManager 默认返回[GridLayoutManager] 4列
     */
    fun getLayoutManager(context: Context): RecyclerView.LayoutManager{
        return LinearLayoutManager(context)
    }

    /**
     * 注入布局
     *
     * Adapter中的onCreateViewHolder回调
     *
     * @return 返回继承[MediaFileAdapter.BaseViewHolder]的自定义ViewHolder
     */
    fun onCreateViewHolder(parent: ViewGroup): MediaFolderPopupAdapter.BaseViewHolder

}