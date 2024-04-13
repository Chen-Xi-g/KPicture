package com.griffin.kp.core

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.griffin.kp.KPicture
import com.griffin.kp.R
import com.griffin.kp.theme.KPictureTheme
import com.griffin.kp.utils.AlbumHelper
import com.griffin.kp.utils.asColor
import com.griffin.kp.utils.asDrawable
import com.griffin.kp.utils.colorToDrawable

class KPictureLayout : LinearLayout {

    constructor(context: Context) : super(context) {
        initView(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    /**
     * 获取主题
     */
    private val theme: KPictureTheme? = KPicture.theme

    /**
     * 状态栏是否可见
     *
     * @return true 可见 false 不可见
     */
    var visibleStatus: Boolean = true
        set(value) {
            field = value
            statusLayout?.visibility = if (value) VISIBLE else GONE
        }

    /**
     * 是否显示导航栏
     */
    var visibleNav: Boolean = true
        set(value) {
            field = value
            navLayout?.visibility = if (value) VISIBLE else GONE
        }

    /**
     * 状态栏背景
     */
    var statusBg: Drawable? = null
        get() = if (field == null) {
            theme?.statusBg ?: ColorDrawable(Color.BLACK)
        } else {
            field
        }

    /**
     * 状态栏背景颜色
     */
    @ColorInt
    var statusBgColor: Int? = null

    /**
     * 导航栏背景
     */
    var navBg: Drawable? = null
        get() = if (field == null) {
            theme?.navBg ?: ColorDrawable(Color.BLACK)
        } else {
            field
        }

    /**
     * 导航栏背景颜色
     */
    @ColorInt
    var navBgColor: Int? = null

    /**
     * 关闭图标
     */
    var closeIcon: Drawable? = null
        get() = if (field == null) {
            theme?.closeIcon
        } else {
            field
        }

    /**
     * 选择相册背景
     */
    var albumBg: Drawable? = null
        get() = if (field == null) {
            theme?.albumBg ?: ColorDrawable(Color.BLACK)
        } else {
            field
        }

    /**
     * 选择相册背景颜色
     */
    @ColorInt
    var albumBgColor: Int? = null

    /**
     * 选择相册字体颜色
     */
    @ColorInt
    var albumTextColor: Int? = null
        get() = if (field == null) {
            theme?.albumTextColor ?: Color.BLACK
        } else {
            field
        }

    /**
     * 选择相册字体大小
     */
    var albumTextSize: Float? = null
        get() = if (field == null) {
            theme?.albumTextSize ?: 14f
        } else {
            field
        }

    /**
     * 选择相册右侧图标
     */
    var albumRightIcon: Drawable? = null
        get() = if (field == null) {
            theme?.albumRightIcon
        } else {
            field
        }

    /**
     * 相册列表背景
     */
    var albumListBg: Drawable? = null
        get() = if (field == null) {
            theme?.albumListBg
        } else {
            field
        }

    /**
     * 相册列表背景颜色
     */
    @ColorInt
    var albumListBgColor: Int? = null

    /**
     * 相册列表间距
     */
    var albumListSpace: Float? = null
        get() = if (field == null) {
            theme?.albumListSpace ?: 0f
        } else {
            field
        }

    /**
     * 未选中选框
     */
    var unSelectColor: Drawable? = null
        get() = if (field == null) {
            theme?.unSelectColor
        } else {
            field
        }

    /**
     * 选中选框
     */
    var selectColor: Drawable? = null
        get() = if (field == null) {
            theme?.selectBg
        } else {
            field
        }

    /**
     * 已选择图片背景
     */
    var selectBg: Drawable? = null
        get() = if (field == null) {
            theme?.selectBg
        } else {
            field
        }

    /**
     * 视频文件底部阴影
     */
    var videoShadow: Drawable? = null
        get() = if (field == null) {
            theme?.videoShadow
        } else {
            field
        }

    /**
     * 视频文件图标
     */
    var videoIcon: Drawable? = null
        get() = if (field == null) {
            theme?.videoIcon
        } else {
            field
        }

    /**
     * 视频文件时长字体颜色
     */
    @ColorInt
    var videoDurationColor: Int? = null
        get() = if (field == null) {
            theme?.videoDurationColor
        } else {
            field
        }

    /**
     * 预览字体颜色
     */
    @ColorInt
    var previewTextColor: Int? = null
        get() = if (field == null) {
            theme?.previewTextColor
        } else {
            field
        }

    /**
     * 预览不可点击字体颜色
     */
    @ColorInt
    var previewTextDisableColor: Int? = null
        get() = if (field == null) {
            theme?.previewTextDisableColor
        } else {
            field
        }

    /**
     * 预览字体大小
     */
    var previewTextSize: Float? = null
        get() = if (field == null) {
            theme?.previewTextSize ?: 14f
        } else {
            field
        }

    /**
     * 预览背景
     */
    var previewBg: Drawable? = null
        get() = if (field == null) {
            theme?.previewBg
        } else {
            field
        }

    /**
     * 预览背景颜色
     */
    @ColorInt
    var previewBgColor: Int? = null


    /**
     * 预览不可点击背景
     */
    var previewDisableBg: Drawable? = null
        get() = if (field == null) {
            theme?.previewDisableBg
        } else {
            field
        }

    /**
     * 预览不可点击背景颜色
     */
    @ColorInt
    var previewDisableBgColor: Int? = null

    /**
     * 发送按钮背景
     */
    var sendBg: Drawable? = null
        get() = if (field == null) {
            theme?.sendBg
        } else {
            field
        }

    /**
     * 发送按钮背景颜色
     */
    @ColorInt
    var sendBgColor: Int? = null

    /**
     * 发送按钮不可点击背景
     */
    var sendDisableBg: Drawable? = null
        get() = if (field == null) {
            theme?.sendDisableBg
        } else {
            field
        }

    /**
     * 发送按钮不可点击背景颜色
     */
    @ColorInt
    var sendDisableBgColor: Int? = null

    /**
     * 发送字体颜色
     */
    @ColorInt
    var sendTextColor: Int? = null
        get() = if (field == null) {
            theme?.sendTextColor ?: Color.BLACK
        } else {
            field
        }

    /**
     * 发送不可点击字体颜色
     */
    @ColorInt
    var sendTextDisableColor: Int? = null
        get() = if (field == null) {
            theme?.sendTextDisableColor ?: Color.BLACK
        } else {
            field
        }

    /**
     * 发送字体大小
     */
    var sendTextSize: Float? = null
        get() = if (field == null) {
            theme?.sendTextSize ?: 14f
        } else {
            field
        }

    /**
     * 状态栏布局
     */
    private var statusLayout: KPictureStatusLayout? = null

    /**
     * 相册列表布局
     */
    private var albumLayout: KPictureAlbumLayout? = null

    /**
     * 导航栏布局
     */
    private var navLayout: KPictureNavLayout? = null

    /**
     * 相册列表
     */
    private val albumPopup: AlbumPopup by lazy {
        AlbumPopup(context)
    }

    private fun initView(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.KPictureLayout)
        try {
            visibleStatus =
                attributes.getBoolean(R.styleable.KPictureLayout_visible_status, true)
            visibleNav = attributes.getBoolean(R.styleable.KPictureLayout_visible_nav, true)
            statusBg = attributes.getDrawable(R.styleable.KPictureLayout_status_bg)
            statusBgColor = attributes.getColor(
                R.styleable.KPictureLayout_status_color,
                -1
            )
            navBg = attributes.getDrawable(R.styleable.KPictureLayout_nav_bg)
            navBgColor = attributes.getColor(
                R.styleable.KPictureLayout_nav_color,
                -1
            )
            closeIcon = attributes.getDrawable(R.styleable.KPictureLayout_close_icon)
            albumBg =
                attributes.getDrawable(R.styleable.KPictureLayout_album_bg)
            albumBgColor = attributes.getColor(
                R.styleable.KPictureLayout_album_bg_color,
                -1
            )
            albumTextColor = attributes.getColor(
                R.styleable.KPictureLayout_album_text_color,
                context.asColor(R.color.kp_album_text)
            )
            albumTextSize = attributes.getDimension(
                R.styleable.KPictureLayout_album_text_size,
                14f
            )
            albumRightIcon = attributes.getDrawable(R.styleable.KPictureLayout_album_right_icon)
            albumListBg = attributes.getDrawable(R.styleable.KPictureLayout_album_list_bg)
            albumListBgColor = attributes.getColor(
                R.styleable.KPictureLayout_album_list_bg_color,
                -1
            )
            albumListSpace = attributes.getDimension(
                R.styleable.KPictureLayout_album_list_space,
                dp2px(1f).toFloat()
            )
            unSelectColor = attributes.getDrawable(R.styleable.KPictureLayout_un_select)
            selectColor = attributes.getDrawable(R.styleable.KPictureLayout_on_select)
            selectBg =
                attributes.getDrawable(R.styleable.KPictureLayout_select_bg)
            videoShadow = attributes.getDrawable(R.styleable.KPictureLayout_video_shadow)
            videoIcon = attributes.getDrawable(R.styleable.KPictureLayout_video_icon)
            videoDurationColor = attributes.getColor(
                R.styleable.KPictureLayout_video_duration_color,
                context.asColor(R.color.kp_video_duration_text)
            )
            previewTextColor = attributes.getColor(
                R.styleable.KPictureLayout_preview_text_color,
                context.asColor(R.color.kp_preview_text)
            )
            previewTextDisableColor = attributes.getColor(
                R.styleable.KPictureLayout_preview_text_disable_color,
                context.asColor(R.color.kp_preview_text_disable)
            )
            previewTextSize = attributes.getDimension(
                R.styleable.KPictureLayout_preview_text_size,
                14f
            )
            previewBg =
                attributes.getDrawable(R.styleable.KPictureLayout_preview_bg)
            previewBgColor = attributes.getColor(
                R.styleable.KPictureLayout_preview_bg_color,
                -1
            )
            previewDisableBg = attributes.getDrawable(R.styleable.KPictureLayout_preview_disable_bg)
            previewDisableBgColor = attributes.getColor(
                R.styleable.KPictureLayout_preview_disable_bg_color,
                -1
            )
            sendBg =
                attributes.getDrawable(R.styleable.KPictureLayout_send_bg)
            sendBgColor = attributes.getColor(
                R.styleable.KPictureLayout_send_bg_color,
                context.asColor(R.color.kp_send_bg)
            )
            sendDisableBg = attributes.getDrawable(R.styleable.KPictureLayout_send_disable_bg)
            sendDisableBgColor = attributes.getColor(
                R.styleable.KPictureLayout_send_disable_bg_color,
                -1
            )
            sendTextColor = attributes.getColor(
                R.styleable.KPictureLayout_send_text_color,
                context.asColor(R.color.kp_send_text)
            )
            sendTextDisableColor = attributes.getColor(
                R.styleable.KPictureLayout_send_text_disable_color,
                context.asColor(R.color.kp_send_text_disable)
            )
            sendTextSize = attributes.getDimension(
                R.styleable.KPictureLayout_send_text_size,
                14f
            )
        } finally {
            attributes.recycle()
        }
        orientation = VERTICAL
        if (visibleStatus) {
            statusLayout = createStatusLayout()
            statusEditMode()
            addView(statusLayout)
        }
        albumLayout = createAlbumLayout()
        val albumLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 0)
        albumLayoutParams.weight = 1f // 使用权重属性
        addView(albumLayout, albumLayoutParams)
        AlbumHelper.scanAllAlbum(context) {
            if (it.isEmpty()) return@scanAllAlbum
            if (visibleStatus) {
                if (it.isNotEmpty()) {
                    statusLayout?.tvAlbumName?.text = it[0].folderName
                }
                statusLayout?.onAlbumChangeListener = {
                    albumPopup.adapter.setList(it)
                    if (albumPopup.isShowing) {
                        albumPopup.dismiss()
                    } else {
                        albumPopup.showAsDropDown(statusLayout)
                    }
                }
            }
            albumLayout?.adapter?.setList(it[0].mediaList)
        }
        albumPopup.adapter.setOnClickItemListener { folder ->
            if (folder.mediaList.isEmpty()) {
                AlbumHelper.queryAlbumByBucketId(context, folder.bucketId) { media ->
                    statusLayout?.tvAlbumName?.text = folder.folderName
                    albumLayout?.adapter?.setList(media)
                    folder.mediaList.addAll(media)
                    albumPopup.dismiss()
                }
            }else{
                statusLayout?.tvAlbumName?.text = folder.folderName
                albumLayout?.adapter?.setList(folder.mediaList)
                albumPopup.dismiss()
            }
        }
        if (visibleNav) {
            navLayout = createNavLayout()
            navLayout?.disablePreview()
            navLayout?.disableSend()
            navEditMode()
            addView(navLayout)
        }
    }

    private fun statusEditMode() {
        if (isInEditMode) {
            background = context.colorToDrawable(R.color.kp_status_bg)
            statusLayout?.ivCloseIcon?.setImageResource(R.drawable.kp_ic_action_close)
            statusLayout?.llAlbum?.background = context.asDrawable(R.drawable.kp_shape_album_bg)
            statusLayout?.tvAlbumName?.setTextColor(context.asColor(R.color.kp_album_text))
            statusLayout?.tvAlbumName?.textSize = 14f
            statusLayout?.tvAlbumName?.text = "最近相册"
            statusLayout?.ivAlbumRightIcon?.setImageDrawable(context.asDrawable(R.drawable.kp_ic_action_arrow_down))
        }
    }

    private fun navEditMode() {
        if (isInEditMode) {
            navLayout?.tvPreview?.setTextColor(context.asColor(R.color.kp_preview_text))
            navLayout?.tvPreview?.textSize = 14f
            navLayout?.tvSend?.background = context.asDrawable(R.drawable.kp_shape_send_bg)
            navLayout?.tvSend?.setTextColor(context.asColor(R.color.kp_send_text))
            navLayout?.tvSend?.textSize = 14f
        }
    }

    private fun createNavLayout(): KPictureNavLayout {
        return KPictureNavLayout(context).apply {
            navBg = this@KPictureLayout.navBg
            navBgColor = this@KPictureLayout.navBgColor
            previewTextColor = this@KPictureLayout.previewTextColor
            previewTextDisableColor = this@KPictureLayout.previewTextDisableColor
            previewTextSize = this@KPictureLayout.previewTextSize
            previewBg = this@KPictureLayout.previewBg
            previewBgColor = this@KPictureLayout.previewBgColor
            previewDisableBg = this@KPictureLayout.previewDisableBg
            previewDisableBgColor = this@KPictureLayout.previewDisableBgColor
            sendBg = this@KPictureLayout.sendBg
            sendBgColor = this@KPictureLayout.sendBgColor
            sendDisableBg = this@KPictureLayout.sendDisableBg
            sendDisableBgColor = this@KPictureLayout.sendDisableBgColor
            sendTextColor = this@KPictureLayout.sendTextColor
            sendTextDisableColor = this@KPictureLayout.sendTextDisableColor
            sendTextSize = this@KPictureLayout.sendTextSize
        }
    }

    private fun createAlbumLayout(): KPictureAlbumLayout {
        return KPictureAlbumLayout(context).apply {
            albumListBg = this@KPictureLayout.albumListBg
            albumListSpace = this@KPictureLayout.albumListSpace
            albumListBgColor = this@KPictureLayout.albumListBgColor
            unSelectColor = this@KPictureLayout.unSelectColor
            selectColor = this@KPictureLayout.selectColor
            selectBg = this@KPictureLayout.selectBg
            videoShadow = this@KPictureLayout.videoShadow
            videoIcon = this@KPictureLayout.videoIcon
            videoDurationColor = this@KPictureLayout.videoDurationColor
        }
    }

    private fun createStatusLayout(): KPictureStatusLayout {
        return KPictureStatusLayout(context).apply {
            statusBg = this@KPictureLayout.statusBg
            statusBgColor = this@KPictureLayout.statusBgColor
            closeIcon = this@KPictureLayout.closeIcon
            albumBg = this@KPictureLayout.albumBg
            albumBgColor = this@KPictureLayout.albumBgColor
            albumTextColor = this@KPictureLayout.albumTextColor
            albumTextSize = this@KPictureLayout.albumTextSize
            albumRightIcon = this@KPictureLayout.albumRightIcon
        }
    }

    private fun dp2px(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}