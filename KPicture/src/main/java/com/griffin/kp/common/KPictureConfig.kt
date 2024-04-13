package com.griffin.kp.common

import android.provider.MediaStore
import java.lang.Long.max
import java.util.Locale

/**
 * 配置类
 *
 * @param mimeType 文件类型
 * @param fileMaxSize 文件最大大小（KB）
 * @param fileMinSize 文件最小大小（KB）
 * @param fileMaxDuration 文件最大时长（秒）
 * @param fileMinDuration 文件最小时长（秒）
 * @param isGif 是否显示GIF图
 * @param isWebp 是否显示Webp图
 * @param isBmp 是否显示BMP图
 * @param isXmsBmp 是否显示XmsBmp图
 * @param isWapBmp 是否显示WapBmp图
 * @param isHeic 是否显示Heic图
 * @param sort 排序方式 DESC：降序，ASC：升序
 */
class KPictureConfig(
    val mimeType: KPictureMimeType = KPictureMimeType.ALL,
    val fileMaxSize: Long,
    val fileMinSize: Long,
    val fileMaxDuration: Long,
    val fileMinDuration: Long = 0,
    val isGif: Boolean = false,
    val isWebp: Boolean = false,
    val isBmp: Boolean = false,
    val isXmsBmp: Boolean = false,
    val isWapBmp: Boolean = false,
    val isHeic: Boolean = false,
    val sort: String = "DESC"
) {

    companion object {

        /**
         * 默认配置
         */
        val DEFAULT = Builder().build()

        /**
         * 构建配置
         *
         * @param block 配置构建者
         */
        fun build(block: Builder.() -> Unit): KPictureConfig {
            return Builder().apply(block).build()
        }
    }

    /**
     * 配置类构建者
     */
    class Builder {

        /**
         * 文件类型
         */
        var mimeType: KPictureMimeType = KPictureMimeType.ALL

        /**
         * 文件最大大小（KB）
         */
        var fileMaxSize: Long = 0

        /**
         * 文件最小时长（秒）
         */
        var fileMinSize: Long = 0

        /**
         * 文件最大时长（秒）
         */
        var fileMaxDuration: Long = 0

        /**
         * 文件最小时长（秒）
         */
        var fileMinDuration: Long = 0

        /**
         * 是否显示GIF图
         */
        var isGif: Boolean = false

        /**
         * 是否显示Webp图
         */
        var isWebp: Boolean = false

        /**
         * 是否显示BMP图
         */
        var isBmp: Boolean = false

        /**
         * 是否显示XmsBMP图
         */
        var isXmsBmp: Boolean = false

        /**
         * 是否显示WapBMP图
         */
        var isWapBmp: Boolean = false

        /**
         * 是否显示Heic图
         */
        var isHeic: Boolean = false

        /**
         * 排序方式
         */
        var sort: String = "DESC"

        /**
         * 设置文件类型
         */
        fun setMimeType(mimeType: KPictureMimeType): Builder {
            this.mimeType = mimeType
            return this
        }

        /**
         * 设置文件最大大小，单位为KB
         *
         * @param fileMaxSize 文件最大大小（KB）
         */
        fun setFileMaxSize(fileMaxSize: Long): Builder {
            this.fileMaxSize = fileMaxSize
            return this
        }

        /**
         * 设置文件最小时长，单位为秒
         *
         * @param fileMinSize 文件最小时长（秒）
         */
        fun setFileMinSize(fileMinSize: Long): Builder {
            this.fileMinSize = fileMinSize
            return this
        }

        /**
         * 设置文件最大时长，单位为秒
         *
         * @param fileMaxDuration 文件最大时长（秒）
         */
        fun setFileMaxDuration(fileMaxDuration: Long): Builder {
            this.fileMaxDuration = fileMaxDuration
            return this
        }

        /**
         * 设置文件最小时长，单位为秒
         *
         * @param fileMinDuration 文件最小时长（秒）
         */
        fun setFileMinDuration(fileMinDuration: Long): Builder {
            this.fileMinDuration = fileMinDuration
            return this
        }

        /**
         * 设置是否显示GIF图
         *
         * @param isGif 是否显示GIF图
         */
        fun setIsGif(isGif: Boolean): Builder {
            this.isGif = isGif
            return this
        }

        /**
         * 设置是否显示Webp图
         *
         * @param isWebp 是否显示Webp图
         */
        fun setIsWebp(isWebp: Boolean): Builder {
            this.isWebp = isWebp
            return this
        }

        /**
         * 设置是否显示BMP图
         *
         * @param isBmp 是否显示BMP图
         */
        fun setIsBmp(isBmp: Boolean): Builder {
            this.isBmp = isBmp
            return this
        }

        /**
         * 设置是否显示XmsBMP图
         *
         * @param isXmsBmp 是否显示XmsBMP图
         */
        fun setIsXmsBmp(isXmsBmp: Boolean): Builder {
            this.isXmsBmp = isXmsBmp
            return this
        }

        /**
         * 设置是否显示WapBMP图
         *
         * @param isWapBmp 是否显示WapBMP图
         */
        fun setIsWapBmp(isWapBmp: Boolean): Builder {
            this.isWapBmp = isWapBmp
            return this
        }

        /**
         * 设置是否显示Heic图
         *
         * @param isHeic 是否显示Heic图
         */
        fun setIsHeic(isHeic: Boolean): Builder {
            this.isHeic = isHeic
            return this
        }

        /**
         * 设置排序方式
         *
         * @param sort 排序方式 DESC：降序，ASC：升序
         */
        fun setSort(sort: String): Builder {
            this.sort = sort
            return this
        }

        fun build(): KPictureConfig {
            return KPictureConfig(
                mimeType,
                fileMaxSize,
                fileMinSize,
                fileMaxDuration,
                fileMinDuration,
                isGif,
                isWebp,
                isBmp,
                isXmsBmp,
                isWapBmp,
                isHeic
            )
        }

    }

    /**
     * 获取时长条件
     */
    fun getDurationCondition(): String {
        val maxS = if (fileMaxDuration == 0L) Long.MAX_VALUE else fileMaxDuration
        return String.format(
            Locale.CHINA,
            "%d <%s ${MediaStore.Files.FileColumns.DURATION} and ${MediaStore.Files.FileColumns.DURATION} <= %d",
            max((0L).coerceAtLeast(fileMinDuration), 0L),
            "=",
            maxS
        )
    }

    /**
     * 获取文件大小条件
     */
    fun getFileSizeCondition(): String {
        val maxS = if (fileMaxSize == 0L) Long.MAX_VALUE else fileMaxSize
        return String.format(
            Locale.CHINA,
            "%d <%s ${MediaStore.MediaColumns.SIZE} and ${MediaStore.MediaColumns.SIZE} <= %d",
            max(0, fileMinSize),
            "=",
            maxS
        )
    }

}