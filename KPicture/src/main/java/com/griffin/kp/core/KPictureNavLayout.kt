package com.griffin.kp.core

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.griffin.kp.KPicture
import com.griffin.kp.R
import com.griffin.kp.utils.asColor
import com.griffin.kp.utils.asDrawable

class KPictureNavLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    /**
     * 导航栏背景
     */
    var navBg: Drawable? = null
        get() = field ?: KPicture.theme?.navBg
        set(value) {
            field = value
            background = value
        }

    /**
     * 导航栏背景颜色
     */
    @ColorInt
    var navBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                setBackgroundColor(value)
            }
        }

    /**
     * 预览字体颜色
     */
    @ColorInt
    var previewTextColor: Int? = null
        set(value) {
            field = value
            tvPreview?.setTextColor(value ?: Color.WHITE)
        }

    /**
     * 预览不可点击字体颜色
     */
    @ColorInt
    var previewTextDisableColor: Int? = null
        set(value) {
            field = value
            tvPreview?.setTextColor(value ?: Color.GRAY)
        }

    /**
     * 预览字体大小
     */
    var previewTextSize: Float? = null
        set(value) {
            field = value
            tvPreview?.textSize = value ?: 14f
        }

    /**
     * 预览背景
     */
    var previewBg: Drawable? = null
        set(value) {
            field = value
            tvPreview?.background = value
        }

    /**
     * 预览背景颜色
     */
    @ColorInt
    var previewBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                tvPreview?.setBackgroundColor(value)
            }
        }

    /**
     * 预览不可点击背景
     */
    var previewDisableBg: Drawable? = null
        set(value) {
            field = value
            tvPreview?.background = value
        }

    /**
     * 预览不可点击背景颜色
     */
    @ColorInt
    var previewDisableBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1 ) {
                tvPreview?.setBackgroundColor(value)
            }
        }

    /**
     * 发送按钮背景
     */
    var sendBg: Drawable? = null
        set(value) {
            field = value
            tvSend?.background = value
        }

    /**
     * 发送按钮背景颜色
     */
    @ColorInt
    var sendBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1 ) {
                tvSend?.setBackgroundColor(value)
            }
        }

    /**
     * 发送按钮不可点击背景
     */
    var sendDisableBg: Drawable? = null
        set(value) {
            field = value
            tvSend?.background = value
        }

    /**
     * 发送按钮不可点击背景颜色
     */
    @ColorInt
    var sendDisableBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1 ) {
                tvSend?.setBackgroundColor(value)
            }
        }

    /**
     * 发送字体颜色
     */
    @ColorInt
    var sendTextColor: Int? = null
        set(value) {
            field = value
            tvSend?.setTextColor(value ?: Color.WHITE)
        }

    /**
     * 发送不可点击字体颜色
     */
    @ColorInt
    var sendTextDisableColor: Int? = null
        set(value) {
            field = value
            tvSend?.setTextColor(value ?: Color.GRAY)
        }

    /**
     * 发送字体大小
     */
    var sendTextSize: Float? = null
        set(value) {
            field = value
            tvSend?.textSize = value ?: 14f
        }

    var tvPreview: TextView? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_tv_preview)
            }
            return field
        }

    var tvSend: TextView? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_tv_send)
            }
            return field
        }

    /**
     * 是否启用预览按钮
     */
    var enablePreview: Boolean = false
        set(value) {
            field = value
            if (value) {
                enablePreview()
            } else {
                disablePreview()
            }
        }

    /**
     * 是否启用发送按钮
     */
    var enableSend: Boolean = false
        set(value) {
            field = value
            if (value) {
                enableSend()
            } else {
                disableSend()
            }
        }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.kp_nav_layout, this, true)
        if (isInEditMode) {
            tvPreview?.setTextColor(context.asColor(R.color.kp_preview_text))
            tvPreview?.textSize = 14f
            tvSend?.background = context.asDrawable(R.drawable.kp_shape_send_bg)
            tvSend?.setTextColor(context.asColor(R.color.kp_send_text))
            tvSend?.textSize = 14f
        }
    }

    /**
     * 禁用预览按钮
     */
    fun disablePreview() {
        tvPreview?.let {
            it.setTextColor(previewTextDisableColor ?: Color.GRAY)
            if (previewDisableBg != null) {
                it.background = previewDisableBg
            } else if (previewDisableBgColor != null && previewDisableBgColor != -1) {
                it.setBackgroundColor(previewDisableBgColor!!)
            }
        }
    }

    /**
     * 启用预览按钮
     */
    fun enablePreview() {
        tvPreview?.let {
            it.setTextColor(previewTextColor ?: Color.WHITE)
            if (previewBg != null) {
                it.background = previewBg
            } else if (previewBgColor != null && previewBgColor != -1) {
                it.setBackgroundColor(previewBgColor!!)
            }
        }
    }

    /**
     * 禁用发送按钮
     */
    fun disableSend() {
        tvSend?.let {
            it.setTextColor(sendTextDisableColor ?: Color.GRAY)
            if (sendDisableBg != null) {
                it.background = sendDisableBg
            } else if (sendDisableBgColor != null && sendDisableBgColor != -1) {
                it.setBackgroundColor(sendDisableBgColor!!)
            }
        }
    }

    /**
     * 启用发送按钮
     */
    fun enableSend() {
        tvSend?.let {
            it.setTextColor(sendTextColor ?: Color.WHITE)
            if (sendBg != null) {
                it.background = sendBg
            } else if (sendBgColor != null && sendBgColor != -1) {
                it.setBackgroundColor(sendBgColor!!)
            }
        }
    }

}