package com.griffin.kp

import android.annotation.SuppressLint
import android.content.Context
import com.griffin.kp.def.DefaultAlbumPopupConfig
import com.griffin.kp.def.DefaultMediaAdapter
import com.griffin.kp.def.DefaultMediaFolderAdapter
import com.griffin.kp.extend.IAlbumPopupConfig
import com.griffin.kp.extend.IMediaAdapter
import com.griffin.kp.extend.IMediaFolderAdapter
import com.griffin.kp.extend.engine.KPictureImageEngine
import com.griffin.kp.theme.KPictureTheme

/**
 * 全局图片选择配置
 *
 * @author 高国峰
 */
@SuppressLint("StaticFieldLeak")
object KPicture {

    var context: Context? = null

    /**
     * 主题
     */
    var theme: KPictureTheme? = null

    /**
     * 自定义布局
     */
    var mediaFileAdapter: IMediaAdapter = DefaultMediaAdapter()

    /**
     * 自定义相册属性
     */
    var mediaFolderAdapter: IMediaFolderAdapter = DefaultMediaFolderAdapter()

    /**
     * 自定义相册弹窗属性
     */
    var albumPopupConfig: IAlbumPopupConfig = DefaultAlbumPopupConfig()

    /**
     * 图片加载引擎
     */
    var imageEngine: KPictureImageEngine? = null

    /**
     * 初始化
     *
     * 内部会初始化默认的主题，如果需要自定义主题，需要在初始化之后调用 [setTheme] 进行设置
     *
     * @param context 上下文
     */
    fun initialize(context: Context) {
        KPicture.context = context
        theme = KPictureTheme.build(context)
    }

    /**
     * 设置主题全局主题之前，需要先调用 [initialize] 进行初始化
     *
     * @param block 主题配置
     */
    fun setTheme(block: KPictureTheme.Builder.() -> Unit) {
        if (context == null){
            throw IllegalStateException("请先调用 initialize 进行初始化")
        }
        theme = KPictureTheme.build(context!!, block)
    }

}