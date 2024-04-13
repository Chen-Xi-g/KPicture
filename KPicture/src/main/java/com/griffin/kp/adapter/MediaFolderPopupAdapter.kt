package com.griffin.kp.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.extend.IMediaFolderAdapter
import com.griffin.kp.model.AlbumMediaFolder

/**
 * 相册弹窗适配器
 */
class MediaFolderPopupAdapter(
    private val iMediaFolderAdapter: IMediaFolderAdapter
) : RecyclerView.Adapter<MediaFolderPopupAdapter.BaseViewHolder>() {

    /**
     * 模型数据数据
     */
    private var _list: MutableList<AlbumMediaFolder> = mutableListOf()
    val list = _list

    private var onClickItemListener: ((AlbumMediaFolder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return iMediaFolderAdapter.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(_list[position])
        holder.itemView.setOnClickListener {
            onClickItemListener?.invoke(_list[position])
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        /**
         * 绑定数据
         */
        abstract fun onBind(item: AlbumMediaFolder)
    }

    /**
     * 设置Item点击事件监听
     */
    fun setOnClickItemListener(listener: (AlbumMediaFolder) -> Unit){
        onClickItemListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AlbumMediaFolder>) {
        _list.clear()
        _list.addAll(list)
        notifyDataSetChanged()
    }
}
