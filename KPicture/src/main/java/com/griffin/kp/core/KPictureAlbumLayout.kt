package com.griffin.kp.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.griffin.kp.KPicture
import com.griffin.kp.adapter.ItemDecorationAlbumColumns
import com.griffin.kp.adapter.MediaFileAdapter
import com.griffin.kp.adapter.bouncy.BouncyRecyclerView
import com.griffin.kp.extend.IMediaAdapter

class KPictureAlbumLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        initView()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView()
    }

    /**
     * 相册列表背景
     */
    var albumListBg: Drawable? = null
        set(value) {
            field = value
            if (value != null) {
                background = value
            }
        }

    /**
     * 相册列表背景颜色
     */
    @ColorInt
    var albumListBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                setBackgroundColor(value)
            }
        }

    /**
     * 相册列表间距
     */
    var albumListSpace: Float? = null
        set(value) {
            field = value
            if (itemDecoration == null) {
                itemDecoration = ItemDecorationAlbumColumns(
                    value?.toInt() ?: 1,
                    KPicture.theme?.albumColumn ?: 4
                )
                recyclerView.addItemDecoration(itemDecoration!!)
            } else {
                recyclerView.removeItemDecoration(itemDecoration!!)
                itemDecoration = ItemDecorationAlbumColumns(
                    value?.toInt() ?: 1,
                    KPicture.theme?.albumColumn ?: 4
                )
                recyclerView.addItemDecoration(itemDecoration!!)
            }
        }

    /**
     * 未选中选框
     */
    var unSelectColor: Drawable? = null

    /**
     * 选中选框
     */
    var selectColor: Drawable? = null

    /**
     * 已选择图片背景
     */
    var selectBg: Drawable? = null

    /**
     * 视频文件底部阴影
     */
    var videoShadow: Drawable? = null

    /**
     * 视频文件图标
     */
    var videoIcon: Drawable? = null

    /**
     * 视频文件时长字体颜色
     */
    @ColorInt
    var videoDurationColor: Int? = null

    /**
     * 添加间距
     */
    private var itemDecoration: RecyclerView.ItemDecoration? = null

    /**
     * 自定义相册列表属性
     */
    private var iMediaAdapter: IMediaAdapter = KPicture.mediaFileAdapter

    /**
     * 创建RecyclerView
     */
    val recyclerView: BouncyRecyclerView by lazy {
        BouncyRecyclerView(context).apply {
            layoutManager = iMediaAdapter.getLayoutManager(context)
        }
    }

    /**
     * 媒体列表Adapter
     */
    val adapter: MediaFileAdapter by lazy {
        MediaFileAdapter(iMediaAdapter)
    }

    private fun initView() {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(recyclerView, layoutParams)
        recyclerView.adapter = adapter
    }

}