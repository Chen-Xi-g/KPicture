package com.griffin.kp.def

import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import com.griffin.kp.R
import com.griffin.kp.extend.IAlbumPopupConfig
import com.griffin.kp.utils.drawable

class DefaultAlbumPopupConfig : IAlbumPopupConfig {

    override fun popupMaxHeightRatio(): Float = 0.6f

    override fun popupAnimationStyle(): Int = R.style.kp_popup_animation

    override fun popupBackgroundColor(): Int = R.color.kp_popup_bg

    override fun popupFolderBackgroundColor(): Drawable? = R.drawable.kp_shape_album_rv_bg.drawable

    override fun showFolderAnimation(): Animation {
        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, -1f,
            Animation.RELATIVE_TO_SELF, 0f
        )
        translateAnimation.duration = 300
        translateAnimation.interpolator = DecelerateInterpolator()
        translateAnimation.setAnimationListener(null)
        return translateAnimation
    }

    override fun hideFolderAnimation(): Animation {
        val translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, -1f
        )
        translateAnimation.duration = 300 // 设置动画时长
        translateAnimation.interpolator = DecelerateInterpolator() // 设置插值器
        translateAnimation.setAnimationListener(null)
        return translateAnimation
    }
}