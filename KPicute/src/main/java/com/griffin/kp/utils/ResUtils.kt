package com.griffin.kp.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * 获取Drawable
 *
 * @param drawableRes 传入Drawable的资源
 *
 * @return 返回具体的[Drawable]
 */
internal fun Context.asDrawable(@DrawableRes drawableRes: Int): Drawable?{
    return ContextCompat.getDrawable(this, drawableRes)
}

/**
 * 获取ColorInt
 *
 * @param colorRes 传入Color的资源
 *
 * @return 返回ColorInt
 */
@ColorInt
internal fun Context.asColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

/**
 * 获取Dimens值
 *
 * @param dimenRes 传入Dimens的资源
 *
 * @return 返回具体的Dimens值
 */
internal fun Context.asDimen(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

/**
 * ColorRes 转换为 Drawable
 *
 * @param colorRes 传入Color的资源
 *
 * @return
 */
internal fun Context.colorToDrawable(@ColorRes colorRes: Int): Drawable{
    return ColorDrawable(this.asColor(colorRes))
}