package com.griffin.kp.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.extend.IMediaAdapter
import com.griffin.kp.model.AlbumMediaFile

/**
 * 相册列表适配器
 *
 * 通过实现 [IMediaAdapter] 接口，可以注入自定义相册列表
 *
 * @param iMediaAdapter 自定义相册列表接口
 */
@SuppressLint("NotifyDataSetChanged")
class MediaFileAdapter(
    private val iMediaAdapter: IMediaAdapter
) : RecyclerView.Adapter<MediaFileAdapter.BaseViewHolder>() {

    /**
     * 模型数据数据
     */
    private var _list: MutableList<AlbumMediaFile> = mutableListOf()
    val list = _list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return iMediaAdapter.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(_list[position])
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        /**
         * 绑定数据
         */
        abstract fun onBind(item: AlbumMediaFile)
    }

    fun setList(list: List<AlbumMediaFile>) {
        _list.clear()
        _list.addAll(list)
        notifyDataSetChanged()
    }

}
