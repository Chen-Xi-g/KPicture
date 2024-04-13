package com.griffin.kp.theme

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.griffin.kp.utils.asColor
import com.griffin.kp.utils.asDimen
import com.griffin.kp.utils.asDrawable
import com.griffin.kp.utils.colorToDrawable
import com.griffin.kp.KPicture
import com.griffin.kp.R

/**
 * 全局主题设置
 *
 * 默认的主题样式为微信样式，可以通过[KPicture.setTheme]方法自定义
 *
 * @param statusBg 状态栏背景
 * @param navBg 导航栏背景
 * @param closeIcon 关闭图标
 * @param albumBg 选择相册背景
 * @param albumTextColor 选择相册字体颜色
 * @param albumTextSize 选择相册字体大小
 * @param albumRightIcon 选择相册右侧Icon
 * @param albumListBg 相册列表背景
 * @param albumColumn 相册列表列数
 * @param albumListSpace 列表间距
 * @param unSelectColor 未选中选框
 * @param selectBg 选中选框背景
 * @param selectImgBg 已选择图片背景
 * @param videoShadow 视频文件底部阴影
 * @param videoIcon 视频文件图标
 * @param videoDurationColor 视频文件时长字体颜色
 * @param previewTextColor 预览字体颜色
 * @param previewTextDisableColor 预览不可点击字体颜色
 * @param previewTextSize 预览字体大小
 * @param previewBg 预览背景
 * @param previewDisableBg 预览不可点击背景
 * @param sendBg 发送按钮背景
 * @param sendDisableBg 发送不可点击字体颜色
 * @param sendTextColor 发送字体颜色
 * @param sendTextDisableColor 发送不可点击字体颜色
 * @param sendTextSize 发送字体大小
 */
class KPictureTheme(
    val statusBg: Drawable,
    val navBg: Drawable,
    val closeIcon: Drawable,
    val albumBg: Drawable,
    @ColorInt val albumTextColor: Int,
    val albumTextSize: Float,
    val albumRightIcon: Drawable,
    val albumListBg: Drawable,
    val albumColumn: Int,
    val albumListSpace: Float,
    val unSelectColor: Drawable,
    val selectBg: Drawable,
    val selectImgBg: Drawable,
    val videoShadow: Drawable,
    val videoIcon: Drawable,
    @ColorInt val videoDurationColor: Int,
    @ColorInt val previewTextColor: Int,
    @ColorInt val previewTextDisableColor: Int,
    val previewTextSize: Float,
    val previewBg: Drawable?,
    val previewDisableBg: Drawable?,
    val sendBg: Drawable,
    val sendDisableBg: Drawable,
    @ColorInt val sendTextColor: Int,
    @ColorInt val sendTextDisableColor: Int,
    val sendTextSize: Float
) {

    companion object {
        /**
         * 默认主题与微信相似
         *
         * @param context 上下文
         * @param block 配置主题
         *
         * @return 返回 [KPictureTheme] 实例
         */
        fun build(context: Context, block: Builder.() -> Unit = {}): KPictureTheme {
            return Builder(context).apply(block).build()
        }
    }

    /**
     * Builder类用于构建 [KPictureTheme] 实例
     */
    class Builder(private val context: Context) {
        /**
         * 状态栏背景
         */
        private var statusBg: Drawable = context.colorToDrawable(R.color.kp_status_bg)

        /**
         * 导航栏背景
         */
        private var navBg: Drawable = context.colorToDrawable(R.color.kp_nav_bg)

        /**
         * 关闭图标
         */
        private var closeIcon: Drawable = context.asDrawable(R.drawable.kp_ic_action_close)

        /**
         * 选择相册背景
         */
        private var albumBg: Drawable = context.asDrawable(R.drawable.kp_shape_album_bg)

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
        private var albumRightIcon: Drawable =
            context.asDrawable(R.drawable.kp_ic_action_arrow_down)

        /**
         * 相册列表列数
         */
        private var albumColumn: Int = 4

        /**
         * 相册列表背景
         */
        private var albumListBg: Drawable = context.colorToDrawable(R.color.kp_album_list_bg)

        /**
         * 列表间距
         */
        private var albumListSpace: Float = context.asDimen(R.dimen.kp_album_list_space)

        /**
         * 未选中选框
         */
        private var unSelectColor: Drawable =
            context.asDrawable(R.drawable.kp_ic_action_un_checked)

        /**
         * 选中选框
         */
        private var selectBg: Drawable = context.asDrawable(R.drawable.kp_shape_checked)

        /**
         * 已选择图片背景
         */
        private var selectImgBg: Drawable = context.colorToDrawable(R.color.kp_select_bg)

        /**
         * 视频文件底部阴影
         */
        private var videoShadow: Drawable = context.asDrawable(R.drawable.kp_shape_video_shadow)

        /**
         * 视频文件图标
         */
        private var videoIcon: Drawable = context.asDrawable(R.drawable.kp_ic_action_video)

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
         * 预览不可点击字体颜色
         */
        @ColorInt
        private var previewTextDisableColor: Int = context.asColor(R.color.kp_preview_text_disable)

        /**
         * 预览字体大小
         */
        private var previewTextSize: Float = context.asDimen(R.dimen.kp_preview_text_size)

        /**
         * 预览背景
         */
        private var previewBg: Drawable? = null

        /**
         * 预览不可点击背景
         */
        private var previewDisableBg: Drawable? = null

        /**
         * 发送按钮背景
         */
        private var sendBg: Drawable = context.asDrawable(R.drawable.kp_shape_send_bg)

        /**
         * 发送按钮不可点击背景
         */
        private var sendDisableBg: Drawable =
            context.asDrawable(R.drawable.kp_shape_disable_send_bg)

        /**
         * 发送字体颜色
         */
        @ColorInt
        private var sendTextColor: Int = context.asColor(R.color.kp_send_text)

        /**
         * 发送不可点击字体颜色
         */
        @ColorInt
        private var sendTextDisableColor: Int = context.asColor(R.color.kp_send_text_disable)

        /**
         * 发送字体大小
         */
        private var sendTextSize: Float = context.asDimen(R.dimen.kp_send_text_size)

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
        ): Builder {
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
        fun setCloseIcon(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
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
                this.closeIcon = drawable!!
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
        ): Builder {
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
         * 两个参数只会生效一个，优先级为 [colorRes] > [colorStr]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         */
        fun setAlbumTextColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null
        ): Builder {
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置选择相册字体颜色
                this.albumTextColor = colorRes
            } else {
                // 设置选择相册字体颜色
                this.albumTextColor = Color.parseColor(colorStr)
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
        fun setAlbumRightIcon(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
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
                this.albumRightIcon = drawable!!
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
         * 设置相册列表列数
         *
         * @param column 列数
         */
        fun setAlbumColumn(column: Int): Builder {
            // 设置相册列表列数
            this.albumColumn = column
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
        fun setAlbumListSpace(space: Float? = null, @DimenRes dimenRes: Int? = null): Builder {
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
        fun setUnSelectColor(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
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
                this.unSelectColor = drawable!!
            }

            return this
        }

        /**
         * 设置选中选框
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 参数只会生效一个，优先级为 [drawableRes] > [drawable]
         *
         */
        fun setSelectBg(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
            // 检验drawableRes、drawable是否为空，如果为空则异常
            if (drawableRes == null && drawable == null) {
                throw IllegalArgumentException("drawableRes、drawable不能同时为空")
            }
            // 优先级为drawableRes > drawable
            if (drawableRes != null) {
                // 设置选中选框
                this.selectBg = context.asDrawable(drawableRes)
            } else {
                // 设置选中选框
                this.selectBg = drawable!!
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
        fun setSelectImgBg(
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
                // 设置已选择图片背景
                this.selectImgBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置已选择图片背景
                this.selectImgBg = drawable
            } else {
                // 设置已选择图片背景
                this.selectImgBg = context.colorToDrawable(colorRes!!)
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
        fun setVideoShadow(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
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
                this.videoShadow = drawable!!
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
        fun setVideoIcon(
            @DrawableRes drawableRes: Int? = null,
            drawable: Drawable? = null
        ): Builder {
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
                this.videoIcon = drawable!!
            }

            return this
        }

        /**
         * 设置视频文件时长字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [colorRes] > [colorStr]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         */
        fun setVideoDurationColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null
        ): Builder {
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置视频文件时长字体颜色
                this.videoDurationColor = colorRes
            } else {
                // 设置视频文件时长字体颜色
                this.videoDurationColor = Color.parseColor(colorStr)
            }
            return this
        }

        /**
         * 设置预览字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [colorRes] > [colorStr]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         */
        fun setPreviewTextColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null
        ): Builder {
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置预览字体颜色
                this.previewTextColor = colorRes
            } else {
                // 设置预览字体颜色
                this.previewTextColor = Color.parseColor(colorStr)
            }
            return this
        }

        /**
         * 设置预览字体大小
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [size] > [dimenRes]
         *
         * @param size 字体大小，单位为sp
         * @param dimenRes 字体大小资源ID
         */
        fun setPreviewTextSize(size: Float? = null, @DimenRes dimenRes: Int? = null): Builder {
            // 检验size、dimenRes是否为空，如果为空则异常
            if (size == null && dimenRes == null) {
                throw IllegalArgumentException("size、dimenRes不能同时为空")
            }
            // 优先级为size > dimenRes
            if (size != null) {
                // 设置预览字体大小
                this.previewTextSize = size
            } else {
                // 设置预览字体大小
                this.previewTextSize = context.asDimen(dimenRes!!)
            }

            return this
        }

        /**
         * 设置预览背景
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 三个参数只会生效一个，优先级为 [drawableRes] > [drawable] > [colorRes]
         *
         * @param drawableRes 资源ID
         * @param drawable Drawable
         * @param colorRes 颜色ID
         */
        fun setPreviewBg(
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
                // 设置预览背景
                this.previewBg = context.asDrawable(drawableRes)
            } else if (drawable != null) {
                // 设置预览背景
                this.previewBg = drawable
            } else {
                // 设置预览背景
                this.previewBg = context.colorToDrawable(colorRes!!)
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
         * 设置发送字体颜色
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [colorRes] > [colorStr]
         *
         * @param colorRes 颜色ID
         * @param colorStr 颜色字符串
         */
        fun setSendTextColor(
            @ColorInt colorRes: Int? = null,
            colorStr: String? = null
        ): Builder {
            // 检验colorRes、colorStr、color是否为空，如果为空则异常
            if (colorRes == null && colorStr == null) {
                throw IllegalArgumentException("colorRes、colorStr、color不能同时为空")
            }
            // 优先级为colorRes > colorStr > color
            if (colorRes != null) {
                // 设置发送字体颜色
                this.sendTextColor = colorRes
            } else {
                // 设置发送字体颜色
                this.sendTextColor = Color.parseColor(colorStr)
            }
            return this
        }

        /**
         * 设置发送字体大小
         *
         * 参数不能同时为空，否则抛出 [IllegalArgumentException] 异常。
         *
         * 两个参数只会生效一个，优先级为 [size] > [dimenRes]
         *
         * @param size 字体大小，单位为sp
         * @param dimenRes 字体大小资源ID
         */
        fun setSendTextSize(size: Float? = null, @DimenRes dimenRes: Int? = null): Builder {
            // 检验size、dimenRes是否为空，如果为空则异常
            if (size == null && dimenRes == null) {
                throw IllegalArgumentException("size、dimenRes不能同时为空")
            }
            // 优先级为size > dimenRes
            if (size != null) {
                // 设置发送字体大小
                this.sendTextSize = size
            } else {
                // 设置发送字体大小
                this.sendTextSize = context.asDimen(dimenRes!!)
            }

            return this
        }

        /**
         * 构建 [KPictureTheme] 实例
         */
        fun build(): KPictureTheme {
            return KPictureTheme(
                statusBg = statusBg,
                navBg = navBg,
                closeIcon = closeIcon,
                albumBg = albumBg,
                albumTextColor = albumTextColor,
                albumTextSize = albumTextSize,
                albumRightIcon = albumRightIcon,
                albumListBg = albumListBg,
                albumColumn = albumColumn,
                albumListSpace = albumListSpace,
                unSelectColor = unSelectColor,
                selectBg = selectBg,
                selectImgBg = selectImgBg,
                videoShadow = videoShadow,
                videoIcon = videoIcon,
                videoDurationColor = videoDurationColor,
                previewTextColor = previewTextColor,
                previewTextDisableColor = previewTextDisableColor,
                previewTextSize = previewTextSize,
                previewBg = previewBg,
                previewDisableBg = previewDisableBg,
                sendBg = sendBg,
                sendDisableBg = sendDisableBg,
                sendTextColor = sendTextColor,
                sendTextDisableColor = sendTextDisableColor,
                sendTextSize = sendTextSize
            )
        }
    }

}