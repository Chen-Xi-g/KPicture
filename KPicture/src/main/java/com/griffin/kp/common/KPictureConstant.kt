package com.griffin.kp.common

import android.provider.MediaStore

/**
 * 图片选择器常量
 */
object KPictureConstant {

    /**
     * URI，使用content://模式，用于检索内容。
     *
     * 这个URI用于检索所有图像。
     */
    val QUERY_URI = MediaStore.Files.getContentUri("external")

    /**
     * 不包含Gif的查询条件
     */
    const val SQL_NOT_GIF = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/gif')"

    /**
     * 不包含Webp的查询条件
     */
    const val SQL_NOT_WEBP = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/webp')"

    /**
     * 不包含Bmp的查询条件
     */
    const val SQL_NOT_BMP = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/bmp')"

    /**
     * 不包含XmsBmp的查询条件
     */
    const val SQL_NOT_XMS_BMP = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/x-ms-bmp')"

    /**
     * 不包含WapBmp的查询条件
     */
    const val SQL_NOT_WAP_BMP = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/vnd.wap.wbmp')"

    /**
     * 不包含Heic的查询条件
     */
    const val SQL_NOT_HEIC = " AND (${MediaStore.MediaColumns.MIME_TYPE}!='image/heic')"

    /**
     * 查询排序
     */
    const val SQL_ORDER_BY = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC"

    /**
     * 1KB
     */
    const val KB = 1024

    /**
     * 1MB
     */
    const val MB = 1024 * KB

    /**
     * 1GB
     */
    const val GB = 1024 * MB

    /**
     * 要返回哪些列的列表。传递null将返回所有列.
     *
     * 0: MediaStore.Files.FileColumns._ID 媒体ID
     * 1: MediaStore.Files.FileColumns.DATA 媒体路径
     * 2: MediaStore.Files.FileColumns.WIDTH 媒体宽度
     * 3: MediaStore.Files.FileColumns.HEIGHT 媒体高度
     * 4: MediaStore.Files.FileColumns.SIZE 媒体大小
     * 5: MediaStore.Files.FileColumns.DURATION 媒体时长
     * 6: MediaStore.Files.FileColumns.MIME_TYPE 媒体类型
     * 7: MediaStore.Files.FileColumns.DATE_ADDED 媒体添加时间
     * 8: MediaStore.Files.FileColumns.DISPLAY_NAME 媒体名称
     * 9: MediaStore.Files.FileColumns.ORIENTATION 媒体方向
     * 10: MediaStore.Files.FileColumns.BUCKET_ID 媒体文件夹ID
     * 11: MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME 媒体文件夹名称
     * 12: MediaStore.Files.FileColumns.DATE_MODIFIED 媒体修改时间
     */
    val PROJECTION = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.WIDTH,
        MediaStore.Files.FileColumns.HEIGHT,
        MediaStore.Files.FileColumns.SIZE,
        MediaStore.Files.FileColumns.DURATION,
        MediaStore.Files.FileColumns.MIME_TYPE,
        MediaStore.Files.FileColumns.DATE_ADDED,
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.ORIENTATION,
        MediaStore.Files.FileColumns.BUCKET_ID,
        MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
        MediaStore.Files.FileColumns.DATE_MODIFIED
    )

}