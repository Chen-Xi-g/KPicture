package com.griffin.kp.extend.engine

import android.content.Context
import android.widget.ImageView

/**
 * 图片加载引擎
 */
interface KPictureImageEngine {

    /**
     * 根据View宽高加载图片，预览时调用
     *
     * @param context 上下文
     * @param url 图片地址
     * @param targetView 目标View
     * @param width View宽度
     * @param height View高度
     */
    fun loadImage(context: Context, url: String, targetView: ImageView, width: Int, height: Int)

    /**
     * 根据View宽高加载图片，列表中调用
     *
     * @param context 上下文
     * @param url 图片地址
     * @param targetView 目标View
     * @param width View宽度
     * @param height View高度
     */
    fun loadAlbumImage(context: Context, url: String, targetView: ImageView, width: Int, height: Int)

}