package com.griffin.kp.core

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.griffin.kp.R
import com.griffin.kp.utils.asColor
import com.griffin.kp.utils.asDrawable
import com.griffin.kp.utils.colorToDrawable

class KPictureStatusLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    /**
     * 状态栏背景
     */
    var statusBg: Drawable? = null
        set(value) {
            field = value
            background = value
        }

    /**
     * 状态栏背景颜色
     */
    @ColorInt
    var statusBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                setBackgroundColor(value)
            }
        }

    /**
     * 关闭图标
     */
    var closeIcon: Drawable? = null
        set(value) {
            field = value
            ivCloseIcon?.setImageDrawable(value)
        }

    /**
     * 选择相册背景
     */
    var albumBg: Drawable? = null
        set(value) {
            field = value
            llAlbum?.background = value
        }

    /**
     * 选择相册背景颜色
     */
    @ColorInt
    var albumBgColor: Int? = null
        set(value) {
            field = value
            if (value != null && value != -1) {
                llAlbum?.setBackgroundColor(value)
            }
        }

    /**
     * 选择相册字体颜色
     */
    @ColorInt
    var albumTextColor: Int? = null
        set(value) {
            field = value
            tvAlbumName?.setTextColor(value ?: Color.WHITE)
        }

    /**
     * 选择相册字体大小
     */
    var albumTextSize: Float? = null
        set(value) {
            field = value
            tvAlbumName?.textSize = value ?: 14f
        }

    /**
     * 选择相册右侧图标
     */
    var albumRightIcon: Drawable? = null
        set(value) {
            field = value
            ivAlbumRightIcon?.setImageDrawable(value)
        }

    /**
     * 关闭图标
     */
    var ivCloseIcon: ImageView? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_iv_close)
            }
            return field
        }

    /**
     * 选择相册背景
     */
    var llAlbum: LinearLayout? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_ll_album)
            }
            return field
        }

    /**
     * 选择相册文字
     */
    var tvAlbumName: TextView? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_tv_album_name)
            }
            return field
        }

    /**
     * 选择相册右侧图标
     */
    var ivAlbumRightIcon: ImageView? = null
        get() {
            if (field == null) {
                field = findViewById(R.id.kp_iv_arrow)
            }
            return field
        }

    /**
     * 切换相册监听
     */
    var onAlbumChangeListener: (() -> Unit)? = null

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.kp_status_layout, this)
        if (isInEditMode) {
            background = context.colorToDrawable(R.color.kp_status_bg)
            ivCloseIcon?.setImageResource(R.drawable.kp_ic_action_close)
            llAlbum?.background = context.asDrawable(R.drawable.kp_shape_album_bg)
            tvAlbumName?.setTextColor(context.asColor(R.color.kp_album_text))
            tvAlbumName?.textSize = 14f
            tvAlbumName?.text = "最近相册"
            ivAlbumRightIcon?.setImageDrawable(context.asDrawable(R.drawable.kp_ic_action_arrow_down))
        }
        llAlbum?.setOnClickListener {
            onAlbumChangeListener?.invoke()
        }
    }

    /**
     * 设置相册名称
     *
     * @param name 相册名称
     */
    fun setAlbumName(name: String) {
        tvAlbumName?.text = name
    }

}