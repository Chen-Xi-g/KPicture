package com.griffin.kp.extend

import android.graphics.drawable.Drawable
import android.view.animation.Animation
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import com.griffin.kp.R
import com.griffin.kp.utils.drawable

/**
 * 自定义相册弹窗
 */
interface IAlbumPopupConfig {

    /**
     * 弹窗相册列表最大高度比例
     */
    fun popupMaxHeightRatio(): Float = 0.6f

    /**
     * 弹窗动画资源
     */
    @StyleRes
    fun popupAnimationStyle(): Int = R.style.kp_popup_animation

    /**
     * 弹窗背景颜色
     */
    @ColorRes
    fun popupBackgroundColor(): Int = R.color.kp_popup_bg

    /**
     * 弹窗相册列表背景
     */
    fun popupFolderBackgroundColor(): Drawable? = R.drawable.kp_shape_album_rv_bg.drawable

    /**
     * 弹窗显示相册列表的动画
     */
    fun showFolderAnimation(): Animation

    /**
     * 弹窗隐藏相册列表的动画
     */
    fun hideFolderAnimation(): Animation

}