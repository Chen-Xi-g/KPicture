package com.griffin.kp.theme

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.griffin.kp.R
import com.griffin.kp.utils.asColor
import com.griffin.kp.utils.asDimen
import com.griffin.kp.utils.asDrawable
import com.griffin.kp.utils.colorToDrawable

/**
 * 全局主题设置
 *
 * 属性直接写在类中，而不是在`constructor`中，以便开发者清晰地访问各个属性和说明。
 *
 * 使用传统的Builder 模式来构建 [KPictureTheme] 实例，虽然传统的Builder的代码量会显得很臃肿，但是可以更明确地了解如何配置对象。
 * `apply`构建的Builder更加简洁，但是可能会牺牲一些可读性。
 *
 */
class KPictureTheme(private val context: Context) {

    companion object{
        /**
         * 默认主题与微信相似
         *
         * @param context 上下文
         * @param block 配置主题
         *
         * @return 返回 [KPictureTheme] 实例
         */
        fun create(context: Context, block: KPictureTheme.() -> Unit): KPictureTheme{
            return KPictureTheme(context).apply(block)
        }
    }

    /**
     * 状态栏背景
     */
    private var statusBg: Drawable? = null

    /**
     * 导航栏背景
     */
    private var navBg: Drawable? = null

    /**
     * 关闭图标
     */
    private var closeIcon: Drawable? = null

    /**
     * 选择相册背景
     */
    private var albumBg: Drawable? = null

    /**
     * 选择相册字体颜色
     */
    @ColorInt
    private var albumTextColor: Int = 0

    /**
     * 选择相册字体大小
     */
    private var albumTextSize: Float = 0f

    /**
     * 选择相册右侧Icon
     */
    private var albumRightIcon: Drawable? = null

    /**
     * 相册列表背景
     */
    private var albumListBg: Drawable? = null

    /**
     * 列表间距
     */
    private var albumListSpace: Float = 0f

    /**
     * 未选中选框
     */
    private var unSelectColor: Drawable? = null

    /**
     * 选中选框
     */
    private var selectColor: Drawable? = null

    /**
     * 已选择图片背景
     */
    private var selectBg: Drawable? = null

    /**
     * 视频文件底部阴影
     */
    private var videoShadow: Drawable? = null

    /**
     * 视频文件图标
     */
    private var videoIcon: Drawable? = null

    /**
     * 视频文件时长字体颜色
     */
    @ColorInt
    private var videoDurationColor: Int = 0

    /**
     * 预览字体颜色
     */
    @ColorInt
    private var previewTextColor: Int = 0

    /**
     * 发送按钮背景
     */
    private var sendBg: Drawable? = null

    /**
     * Builder类用于构建 [KPictureTheme] 实例
     */
    class Builder(private val context: Context) {
        /**
         * 状态栏背景
         */
        private var statusBg: Drawable? = context.colorToDrawable(R.color.kp_status_color)

        /**
         * 导航栏背景
         */
        private var navBg: Drawable? = context.colorToDrawable(R.color.kp_nav_color)

        /**
         * 关闭图标
         */
        private var closeIcon: Drawable? = context.asDrawable(R.drawable.kp_ic_action_close)

        /**
         * 选择相册背景
         */
        private var albumBg: Drawable? = context.asDrawable(R.drawable.kp_shape_album_bg)

        /**
         * 选择相册字体颜色
         */
        @ColorInt
        private var albumTextColor: Int = context.asColor(R.color.kp_album_text)

        /**
         * 选择相册字体大小
         */
        private var albumTextSize: Float = context.asDimen(R.dimen.kp_album_text_size)

        /**
         * 选择相册右侧Icon
         */
        private var albumRightIcon: Drawable? =
            context.asDrawable(R.drawable.kp_ic_action_arrow_down)

        /**
         * 相册列表背景
         */
        private var albumListBg: Drawable? = context.colorToDrawable(R.color.kp_album_list_bg)

        /**
         * 列表间距
         */
        private var albumListSpace: Float = context.asDimen(R.dimen.kp_album_list_space)

        /**
         * 未选中选框
         */
        private var unSelectColor: Drawable? =
            context.asDrawable(R.drawable.kp_ic_action_un_checked)

        /**
         * 选中选框
         */
        private var selectColor: Drawable? = context.asDrawable(R.drawable.kp_ic_action_checked)

        /**
         * 已选择图片背景
         */
        private var selectBg: Drawable? = context.colorToDrawable(R.color.kp_select_bg)

        /**
         * 视频文件底部阴影
         */
        private var videoShadow: Drawable? = context.asDrawable(R.drawable.kp_shape_video_shadow)

        /**
         * 视频文件图标
         */
        private var videoIcon: Drawable? = context.asDrawable(R.drawable.kp_ic_action_video)

        /**
         * 视频文件时长字体颜色
         */
        @ColorInt
        private var videoDurationColor: Int = context.asColor(R.color.kp_video_duration_text)

        /**
         * 预览字体颜色
         */
        @ColorInt
        private var previewTextColor: Int = context.asColor(R.color.kp_preview_text)

        /**
         * 发送按钮背景
         */
        private var sendBg: Drawable? = context.asDrawable(R.drawable.kp_shape_send_bg)

        /**
         * 设置状态栏背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setStatusBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ) : Builder{
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置状态栏背景
                this.statusBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置状态栏背景
                this.statusBg = drawable
            } else {
                // 设置状态栏背景
                this.statusBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 设置导航栏背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setNavBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ): Builder {
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置导航栏背景
                this.navBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置导航栏背景
                this.navBg = drawable
            } else {
                // 设置导航栏背景
                this.navBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 设置关闭图标
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setCloseIcon(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null) : Builder{
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置关闭图标
                this.closeIcon = context.asDrawable(drawableRes)
            } else {
                // 设置关闭图标
                this.closeIcon = drawable
            }

            return this
        }

        /**
         * 设置选择相册背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setAlbumBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ) : Builder{
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置选择相册背景
                this.albumBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置选择相册背景
                this.albumBg = drawable
            } else {
                // 设置选择相册背景
                this.albumBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 设置选择相册字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [colorRes] > [colorStr] > [color]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         * @param color 颜色
         */
        fun setAlbumTextColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null,
            color: Color? = null
        ) : Builder{
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null && color == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置选择相册字体颜色
                this.albumTextColor = colorRes
            } else if (colorStr != null) {
                // 设置选择相册字体颜色
                this.albumTextColor = Color.parseColor(colorStr)
            } else {
                // 设置选择相册字体颜色
                this.albumTextColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    color!!.toArgb()
                } else {
                    // 默认为白色
                    Color.WHITE
                }
            }

            return this
        }

        /**
         * 设置选择相册字体大小
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [size] > [dimenRes]
         *
         * @param size 字体大小，单位为sp
         * @param dimenRes 字体大小资源ID
         */
        fun setAlbumTextSize(size: Float? = null, @DimenRes dimenRes: Int? = null): Builder {
            // 检验size、dimenRes是否为空，如果为空则异常
            if (size == null && dimenRes == null) {
                throw IllegalArgumentException("size、dimenRes不能同时为空")
            }
            // 优先级为size > dimenRes
            if (size != null) {
                // 设置选择相册字体大小
                this.albumTextSize = size
            } else {
                // 设置选择相册字体大小
                this.albumTextSize = context.asDimen(dimenRes!!)
            }

            return this
        }

        /**
         * 设置选择相册右侧Icon
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setAlbumRightIcon(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置选择相册右侧Icon
                this.albumRightIcon = context.asDrawable(drawableRes)
            } else {
                // 设置选择相册右侧Icon
                this.albumRightIcon = drawable
            }

            return this
        }

        /**
         * 设置相册列表背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setAlbumListBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ): Builder {
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置相册列表背景
                this.albumListBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置相册列表背景
                this.albumListBg = drawable
            } else {
                // 设置相册列表背景
                this.albumListBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 设置列表间距
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [space] > [dimenRes]
         *
         * @param space 间距，单位px，需要自行将dp转换为px
         * @param dimenRes 间距资源ID
         */
        fun setAlbumListSpace(space: Float? = null, @DimenRes dimenRes: Int? = null) : Builder{
            // 检验space、dimenRes是否为空，如果为空则异常
            if (space == null && dimenRes == null) {
                throw IllegalArgumentException("space、dimenRes不能同时为空")
            }
            // 优先级为space > dimenRes
            if (space != null) {
                // 设置列表间距
                this.albumListSpace = space
            } else {
                // 设置列表间距
                this.albumListSpace = context.asDimen(dimenRes!!)
            }

            return this
        }

        /**
         * 设置未选中选框
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setUnSelectColor(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置未选中选框
                this.unSelectColor = context.asDrawable(drawableRes)
            } else {
                // 设置未选中选框
                this.unSelectColor = drawable
            }

            return this
        }

        /**
         * 设置选中选框
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setSelectColor(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置选中选框
                this.selectColor = context.asDrawable(drawableRes)
            } else {
                // 设置选中选框
                this.selectColor = drawable
            }

            return this
        }

        /**
         * 设置已选择图片背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setSelectBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ) : Builder{
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置已选择图片背景
                this.selectBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置已选择图片背景
                this.selectBg = drawable
            } else {
                // 设置已选择图片背景
                this.selectBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 设置视频文件底部阴影
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setVideoShadow(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置视频文件底部阴影
                this.videoShadow = context.asDrawable(drawableRes)
            } else {
                // 设置视频文件底部阴影
                this.videoShadow = drawable
            }

            return this
        }

        /**
         * 设置视频文件图标
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         */
        fun setVideoIcon(@DrawableRes drawableRes: Int? = null, drawable: Drawable? = null): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置视频文件图标
                this.videoIcon = context.asDrawable(drawableRes)
            } else {
                // 设置视频文件图标
                this.videoIcon = drawable
            }

            return this
        }

        /**
         * 设置视频文件时长字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [colorRes] > [colorStr] > [color]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         * @param color 颜色
         */
        fun setVideoDurationColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null,
            color: Color? = null
        ): Builder {
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null && color == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置视频文件时长字体颜色
                this.videoDurationColor = colorRes
            } else if (colorStr != null) {
                // 设置视频文件时长字体颜色
                this.videoDurationColor = Color.parseColor(colorStr)
            } else {
                // 设置视频文件时长字体颜色
                this.videoDurationColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    color!!.toArgb()
                } else {
                    // 默认为白色
                    Color.WHITE
                }
            }

            return this
        }

        /**
         * 设置预览字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [colorRes] > [colorStr] > [color]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         * @param color 颜色
         */
        fun setPreviewTextColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null,
            color: Color? = null
        ) : Builder{
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null && color == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置预览字体颜色
                this.previewTextColor = colorRes
            } else if (colorStr != null) {
                // 设置预览字体颜色
                this.previewTextColor = Color.parseColor(colorStr)
            } else {
                // 设置预览字体颜色
                this.previewTextColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    color!!.toArgb()
                } else {
                    // 默认为白色
                    Color.WHITE
                }
            }

            return this
        }

        /**
         * 设置发送按钮背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setSendBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null,
            @ColorInt colorRes: Int? = null
        ): Builder {
            // 检验drawableRes、drawable、colorRes是否为空，如果为空则异常
            if (drawableRes == null && drawable == null && colorRes == null) {
                throw IllegalArgumentException("drawableRes、drawable、colorRes不能同时为空")
            }
            // 优先级为drawableRes > drawable > colorRes
            if (drawableRes != null) {
                // 设置发送按钮背景
                this.sendBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置发送按钮背景
                this.sendBg = drawable
            } else {
                // 设置发送按钮背景
                this.sendBg = context.colorToDrawable(colorRes!!)
            }

            return this
        }

        /**
         * 构建 [KPictureTheme] 实例
         */
        fun build(): KPictureTheme {
            return KPictureTheme(context).apply {
                statusBg?.let { setStatusBg(it) }
                navBg?.let { setNavBg(it) }
                closeIcon?.let { setCloseIcon(it) }
                albumBg?.let { setAlbumBg(it) }
                setAlbumTextColor(albumTextColor)
                setAlbumTextSize(albumTextSize)
                albumRightIcon?.let { setAlbumRightIcon(it) }
                albumListBg?.let { setAlbumListBg(it) }
                setAlbumListSpace(albumListSpace)
                unSelectColor?.let { setUnSelectColor(it) }
                selectColor?.let { setSelectColor(it) }
                selectBg?.let { setSelectBg(it) }
                videoShadow?.let { setVideoShadow(it) }
                videoIcon?.let { setVideoIcon(it) }
                setVideoDurationColor(videoDurationColor)
                setPreviewTextColor(previewTextColor)
                sendBg?.let { setSendBg(it) }
            }
        }
    }

    fun setStatusBg(drawable: Drawable) {
        this.statusBg = drawable
    }

    fun setNavBg(drawable: Drawable) {
        this.navBg = drawable
    }

    fun setCloseIcon(drawable: Drawable) {
        this.closeIcon = drawable
    }

    fun setAlbumBg(drawable: Drawable) {
        this.albumBg = drawable
    }

    fun setAlbumTextColor(@ColorInt color: Int) {
        this.albumTextColor = color
    }

    fun setAlbumTextSize(size: Float) {
        this.albumTextSize = size
    }

    fun setAlbumRightIcon(drawable: Drawable) {
        this.albumRightIcon = drawable
    }

    fun setAlbumListBg(drawable: Drawable) {
        this.albumListBg = drawable
    }

    fun setAlbumListSpace(space: Float) {
        this.albumListSpace = space
    }

    fun setUnSelectColor(drawable: Drawable) {
        this.unSelectColor = drawable
    }

    fun setSelectColor(drawable: Drawable) {
        this.selectColor = drawable
    }

    fun setSelectBg(drawable: Drawable) {
        this.selectBg = drawable
    }

    fun setVideoShadow(drawable: Drawable) {
        this.videoShadow = drawable
    }

    fun setVideoIcon(drawable: Drawable) {
        this.videoIcon = drawable
    }

    fun setVideoDurationColor(@ColorInt color: Int) {
        this.videoDurationColor = color
    }

    fun setPreviewTextColor(@ColorInt color: Int) {
        this.previewTextColor = color
    }

    fun setSendBg(drawable: Drawable) {
        this.sendBg = drawable
    }

}