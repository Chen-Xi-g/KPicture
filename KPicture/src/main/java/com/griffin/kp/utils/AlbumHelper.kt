package com.griffin.kp.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.griffin.kp.R
import com.griffin.kp.common.KPictureConfig
import com.griffin.kp.common.KPictureConstant.KB
import com.griffin.kp.common.KPictureConstant.PROJECTION
import com.griffin.kp.common.KPictureConstant.QUERY_URI
import com.griffin.kp.common.KPictureConstant.SQL_NOT_BMP
import com.griffin.kp.common.KPictureConstant.SQL_NOT_GIF
import com.griffin.kp.common.KPictureConstant.SQL_NOT_HEIC
import com.griffin.kp.common.KPictureConstant.SQL_NOT_WAP_BMP
import com.griffin.kp.common.KPictureConstant.SQL_NOT_WEBP
import com.griffin.kp.common.KPictureConstant.SQL_NOT_XMS_BMP
import com.griffin.kp.common.KPictureConstant.SQL_ORDER_BY
import com.griffin.kp.common.KPictureMimeType
import com.griffin.kp.model.AlbumMediaFile
import com.griffin.kp.model.AlbumMediaFolder
import com.griffin.kp.utils.MimeTypeUtils.MIME_TYPE_JPEG
import com.griffin.kp.utils.MimeTypeUtils.MIME_TYPE_WEBP
import com.griffin.kp.utils.MimeTypeUtils.isBmp
import com.griffin.kp.utils.MimeTypeUtils.isGif
import com.griffin.kp.utils.MimeTypeUtils.isHeic
import com.griffin.kp.utils.MimeTypeUtils.isVideo


/**
 * 相册帮助类
 */
object AlbumHelper {

    /**
     * 获取配置
     */
    private val config: KPictureConfig = KPictureConfig.DEFAULT

    /**
     * 扫描相册，并获取相册列表，性能较差
     *
     * @param context 上下文
     * @param callback 扫描完成回调
     */
    fun scanAlbum(context: Context, callback: (List<AlbumMediaFolder>) -> Unit) {
        // 扫描所有相册
        {
            val allFolders = mutableListOf<AlbumMediaFolder>()
            context.contentResolver.query(
                QUERY_URI,
                PROJECTION,
                getSelection(),
                getSelectionArgs(),
                getSortOrder()
            )?.use { cursor ->
                val folder = AlbumMediaFolder()
                val files = mutableListOf<AlbumMediaFile>()
                val count = cursor.count
                if (count > 0) {
                    cursor.moveToFirst()
                    do {
                        // 解析本地媒体文件
                        val mediaFile = parseLocalMedia(cursor) ?: continue
                        parseFolder(mediaFile, allFolders)
                        files.add(mediaFile)
                        folder.total++
                    } while (cursor.moveToNext())

                    if (files.isNotEmpty()) {
                        allFolders.sortByDescending(AlbumMediaFolder::total)
                        folder.coverPath = files[0].path
                        folder.coverMimeType = files[0].mimeType
                        folder.folderName = context.getString(R.string.kp_str_album_default_name)
                        folder.mediaList.addAll(files)
                        allFolders.add(0, folder)
                    }
                }
            }
            allFolders.filter { it.mediaList.isNotEmpty() }
        }.runOnBackgroundThread(callback = callback)
    }

    /**
     * 扫描所有相册，不获取相册列表，性能较好
     *
     * @param context 上下文
     * @param callback 扫描完成回调
     */
    fun scanAllAlbum(context: Context, callback: (List<AlbumMediaFolder>) -> Unit) {
        // 扫描所有相册
        {
            val allFolders = mutableListOf<AlbumMediaFolder>()
            context.contentResolver.query(
                QUERY_URI,
                if (isAndroidQ()) PROJECTION else PROJECTION.plus(
                    "COUNT(*) AS count"
                ),
                getSelection(),
                getSelectionArgs(),
                getSortOrder()
            )?.use { cursor ->
                val count = cursor.count
                var totalCount = 0
                if (count > 0) {
                    if (isAndroidQ()) {
                        val countMap: MutableMap<Long, Long> = mutableMapOf()
                        val hashSet: MutableSet<Long> = mutableSetOf()
                        while (cursor.moveToNext()) {
                            val bucketId =
                                cursor.getLong(cursor.getColumnIndexOrThrow(PROJECTION[10]))
                            var newCount = countMap[bucketId]
                            if (newCount == null) {
                                newCount = 1L
                            } else {
                                newCount += 1L
                            }
                            countMap[bucketId] = newCount

                            if (hashSet.contains(bucketId) || !countMap.containsKey(bucketId)) {
                                continue
                            }

                            val mimeType =
                                cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[6])) ?: ""
                            val size = countMap[bucketId] ?: 0
                            val id = cursor.getLong(cursor.getColumnIndexOrThrow(PROJECTION[0]))

                            val folder = AlbumMediaFolder(
                                bucketId = bucketId,
                                folderName = cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        PROJECTION[11]
                                    )
                                ) ?: "",
                                coverPath = MediaUriUtils.getRealPathUri(id, mimeType),
                                coverMimeType = mimeType,
                                total = size.toInt()
                            )
                            allFolders.add(folder)
                            hashSet.add(bucketId)
                        }
                        for (allFolder in allFolders) {
                            val size = countMap[allFolder.bucketId] ?: 0L
                            allFolder.total = size.toInt()
                            totalCount += allFolder.total
                        }
                    } else {
                        cursor.moveToFirst()
                        do {
                            val url = cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[1]))
                            val folderName =
                                cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[11]))
                            val mimeType =
                                cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[6]))
                            val bucketId =
                                cursor.getLong(cursor.getColumnIndexOrThrow(PROJECTION[10]))
                            val size = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                            val folder = AlbumMediaFolder(
                                bucketId = bucketId,
                                folderName = folderName ?: "",
                                coverPath = url,
                                coverMimeType = mimeType ?: "",
                                total = size
                            )
                            allFolders.add(folder)
                            totalCount += size
                        } while (cursor.moveToNext())
                    }
                    val mediaFolder = AlbumMediaFolder()
                    if (cursor.moveToFirst()) {
                        val id = cursor.getLong(cursor.getColumnIndexOrThrow(PROJECTION[0]))
                        val mimeType = cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[6]))
                        mediaFolder.coverPath = if (isAndroidQ()) MediaUriUtils.getRealPathUri(
                            id,
                            mimeType
                        ) else cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[1]))
                        mediaFolder.coverMimeType = mimeType
                    }
                    if (totalCount == 0) return@use allFolders
                    allFolders.sortByDescending(AlbumMediaFolder::total)
                    mediaFolder.total = totalCount
                    mediaFolder.folderName = context.getString(R.string.kp_str_album_default_name)
                    allFolders.add(0, mediaFolder)
                    return@use allFolders
                }
                return@use allFolders
            }
            allFolders
        }.runOnBackgroundThread(callback = callback)
    }

    /**
     * 根据 bucketId 查询相册
     *
     * @param bucketId 相册 ID
     * @param callback 查询完成回调
     */
    fun queryAlbumByBucketId(
        context: Context,
        bucketId: Long,
        callback: (List<AlbumMediaFile>) -> Unit
    ) {
        {
            val mediaFiles = mutableListOf<AlbumMediaFile>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val queryBundle = Bundle()
                queryBundle.putString(ContentResolver.QUERY_ARG_SQL_SELECTION, getSelection(bucketId));
                queryBundle.putStringArray(
                    ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS,
                    getSelectionArgs(bucketId)
                );
                queryBundle.putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER, getSortOrder());
                context.contentResolver.query(QUERY_URI, PROJECTION, queryBundle, null)
            } else {
                context.contentResolver.query(
                    QUERY_URI,
                    PROJECTION,
                    getSelection(bucketId),
                    getSelectionArgs(bucketId),
                    getSortOrder()
                )
            }?.use { cursor ->
                if (cursor.count > 0) {
                    cursor.moveToFirst()
                    do {
                        val mediaFile = parseLocalMedia(cursor) ?: continue
                        mediaFiles.add(mediaFile)
                    } while (cursor.moveToNext())
                }
                return@use mediaFiles
            }
            mediaFiles
        }.runOnBackgroundThread(callback = callback)
    }

    /**
     * 声明要返回哪些行的过滤器，格式为SQL WHERE子句(不包括WHERE本身)。
     * 传递null将返回给定URI的所有行。
     */
    private fun getSelection(): String {
        if (config == null) {
            return ""
        }
        val duration = config.getDurationCondition()
        val fileSize = config.getFileSizeCondition()
        return when (config.mimeType) {
            is KPictureMimeType.ALL -> {
                getSelectionArgsForAllMediaCondition(
                    duration,
                    fileSize,
                    getImageMimeTypeCondition(),
                    getVideoMimeTypeCondition()
                )
            }

            is KPictureMimeType.IMAGE -> {
                getSelectionArgsForImageMediaCondition(fileSize, getImageMimeTypeCondition())
            }

            is KPictureMimeType.VIDEO -> {
                getSelectionArgsForVideoMediaCondition(duration, getVideoMimeTypeCondition(), fileSize)
            }
        }
    }

    /**
     * 声明要返回哪些行的过滤器，格式为SQL WHERE子句(不包括WHERE本身)。
     * 传递null将返回给定URI的所有行。
     */
    private fun getSelection(bucketId: Long): String {
        if (config == null) {
            return ""
        }
        val duration = config.getDurationCondition()
        val fileSize = config.getFileSizeCondition()
        return when (config.mimeType) {
            is KPictureMimeType.ALL -> {
                getSelectionArgsForAllMediaCondition(
                    bucketId,
                    duration,
                    fileSize,
                    getImageMimeTypeCondition(),
                    getVideoMimeTypeCondition()
                )
            }

            is KPictureMimeType.IMAGE -> {
                getSelectionArgsForImageMediaCondition(
                    bucketId,
                    fileSize,
                    getImageMimeTypeCondition()
                )
            }

            is KPictureMimeType.VIDEO -> {
                getSelectionArgsForVideoMediaCondition(
                    bucketId,
                    duration,
                    getVideoMimeTypeCondition(),
                    fileSize
                )
            }
        }
    }

    /**
     * 获取查询参数数组
     *
     * @return 返回查询参数数组
     */
    private fun getSelectionArgs(): Array<String>? {
        if (config == null) {
            return null
        }
        when (config.mimeType) {
            is KPictureMimeType.ALL -> {
                // 获取所有类型
                return arrayOf(
                    MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
                    MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString()
                )
            }

            is KPictureMimeType.IMAGE -> {
                // 获取图片类型
                return arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString())
            }

            is KPictureMimeType.VIDEO -> {
                // 获取视频类型
                return arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString())
            }
        }
    }

    /**
     * 根据 bucketId 查询参数数组
     *
     * @return 返回查询参数数组
     */
    private fun getSelectionArgs(bucketId: Long): Array<String>? {
        if (config == null) {
            return null
        }
        when (config.mimeType) {
            is KPictureMimeType.ALL -> {
                if (bucketId == -1L) {
                    return arrayOf(
                        MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
                        MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString()
                    )
                }
                // 获取所有类型
                return arrayOf(
                    MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
                    MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString(),
                    bucketId.toString()
                )
            }

            is KPictureMimeType.IMAGE -> {
                // 获取图片类型
                if (bucketId == -1L) {
                    return arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString())
                }
                return arrayOf(
                    MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
                    bucketId.toString()
                )
            }

            is KPictureMimeType.VIDEO -> {
                // 获取视频类型
                if (bucketId == -1L) {
                    return arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString())
                }
                return arrayOf(
                    MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString(),
                    bucketId.toString()
                )
            }
        }
    }

    /**
     * 获取视频媒体条件
     *
     * @param duration 视频时长条件
     * @param queryMimeCondition 媒体类型查询条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForVideoMediaCondition(
        duration: String,
        queryMimeCondition: String,
        fileSize: String
    ): String {
        return "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryMimeCondition AND $duration AND $fileSize"
    }

    /**
     * 获取视频媒体条件
     *
     * @param bucketId 相册 ID
     * @param duration 视频时长条件
     * @param queryMimeCondition 媒体类型查询条件
     * @param fileSize 文件大小条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForVideoMediaCondition(
        bucketId: Long,
        duration: String,
        queryMimeCondition: String,
        fileSize: String
    ): String {
        return "(" +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryMimeCondition AND $duration" +
                ")" +
                " AND " +
                (if (bucketId != -1L) MediaStore.Files.FileColumns.BUCKET_ID + "=? AND " else "") + fileSize
    }

    /**
     * 获取所有媒体模式下的查询条件
     *
     * @param duration 时间条件
     * @param fileSize 大小条件
     * @param queryImageMimeType 图片类型查询条件
     * @param queryVideoMimeType 视频类型查询条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForAllMediaCondition(
        duration: String,
        fileSize: String,
        queryImageMimeType: String,
        queryVideoMimeType: String
    ): String {
        return "(${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryImageMimeType OR " +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryVideoMimeType AND " +
                "$duration) AND $fileSize"
    }

    /**
     * 获取所有媒体模式下的查询条件
     *
     * @param bucketId 相册 ID
     * @param duration 时间条件
     * @param fileSize 大小条件
     * @param queryImageMimeType 图片类型查询条件
     * @param queryVideoMimeType 视频类型查询条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForAllMediaCondition(
        bucketId: Long,
        duration: String,
        fileSize: String,
        queryImageMimeType: String,
        queryVideoMimeType: String
    ): String {
        return "(${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryImageMimeType OR " +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryVideoMimeType AND " +
                "$duration) AND " +
                (if (bucketId != -1L) MediaStore.Files.FileColumns.BUCKET_ID + "=? AND " else "") + fileSize
    }

    /**
     * 获取图片模式下的查询条件
     *
     * @param fileSize 文件大小条件
     * @param queryMimeCondition 媒体类型查询条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForImageMediaCondition(
        fileSize: String,
        queryMimeCondition: String
    ): String {
        return "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryMimeCondition AND $fileSize"
    }

    /**
     * 获取图片模式下的查询条件
     *
     * @param bucketId 文件夹 ID
     * @param fileSize 文件大小条件
     * @param queryMimeCondition 媒体类型查询条件
     * @return 返回查询条件字符串
     */
    private fun getSelectionArgsForImageMediaCondition(
        bucketId: Long,
        fileSize: String,
        queryMimeCondition: String
    ): String {
        return "(" +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE}=?$queryMimeCondition" +
                ")" +
                " AND " +
                (if (bucketId != -1L) MediaStore.Files.FileColumns.MEDIA_TYPE + "=? AND" else "") + fileSize
    }

    /**
     * 获取图片媒体类型条件
     *
     * @return 返回媒体类型条件字符串
     */
    private fun getImageMimeTypeCondition(): String {
        val stringBuilder = StringBuilder()

        // 如果配置中不包含 GIF，则添加条件排除 GIF
        if (config?.isGif == false) {
            stringBuilder.append(SQL_NOT_GIF)
        }

        // 如果配置中不包含 WEBP，则添加条件排除 WEBP
        if (config?.isWebp == false) {
            stringBuilder.append(SQL_NOT_WEBP)
        }

        // 如果配置中不包含 BMP，则添加条件排除 BMP
        if (config?.isBmp == false) {
            stringBuilder.append(SQL_NOT_BMP)
        }

        // 如果配置中不包含 XmsBmp，则添加条件排除 XmsBmp
        if (config?.isXmsBmp == false) {
            stringBuilder.append(SQL_NOT_XMS_BMP)
        }

        // 如果配置中不包含 WapBmp，则添加条件排除 WapBmp
        if (config?.isWapBmp == false) {
            stringBuilder.append(SQL_NOT_WAP_BMP)
        }

        // 如果配置中不包含 HEIC，则添加条件排除 HEIC
        if (config?.isHeic == false) {
            stringBuilder.append(SQL_NOT_HEIC)
        }

        return stringBuilder.toString()
    }


    /**
     * 获取视频文件类型条件
     *
     * @return 返回媒体类型条件字符串
     */
    private fun getVideoMimeTypeCondition(): String {
        val stringBuilder = StringBuilder()

        return stringBuilder.toString()
    }

    /**
     * 获取排序方式
     */
    private fun getSortOrder(): String {
        return if (config?.sort == "DESC" || config?.sort.isNullOrEmpty()) SQL_ORDER_BY else (config?.sort
            ?: "ASC")
    }

    /**
     * 解析本地媒体文件
     *
     * 在这里可以过滤不符合条件的媒体文件
     *
     * @param cursor 媒体数据游标
     * @return 返回媒体文件对象
     */
    private fun parseLocalMedia(cursor: Cursor): AlbumMediaFile? {
        // 媒体 ID
        val idColumn = cursor.getColumnIndexOrThrow(PROJECTION[0])
        // 媒体路径
        val dataColumn = cursor.getColumnIndexOrThrow(PROJECTION[1])
        // 媒体宽度
        val widthColumn = cursor.getColumnIndexOrThrow(PROJECTION[2])
        // 媒体高度
        val heightColumn = cursor.getColumnIndexOrThrow(PROJECTION[3])
        // 媒体大小
        val sizeColumn = cursor.getColumnIndexOrThrow(PROJECTION[4])
        // 媒体时长
        val durationColumn = cursor.getColumnIndexOrThrow(PROJECTION[5])
        // 媒体类型
        val mimeTypeColumn = cursor.getColumnIndexOrThrow(PROJECTION[6])
        // 媒体添加时间
        val dateAddedColumn = cursor.getColumnIndexOrThrow(PROJECTION[7])
        // 媒体文件名称
        val fileNameColumn = cursor.getColumnIndexOrThrow(PROJECTION[8])
        // 媒体方向
        val orientationColumn = cursor.getColumnIndexOrThrow(PROJECTION[9])
        // 媒体文件夹 ID
        val bucketIdColumn = cursor.getColumnIndexOrThrow(PROJECTION[10])
        // 媒体文件夹名称
        val folderNameColumn = cursor.getColumnIndexOrThrow(PROJECTION[11])

        // 获取各列的值
        val id = cursor.getLong(idColumn)
        val dateAdded = cursor.getLong(dateAddedColumn)
        var mimeType = cursor.getString(mimeTypeColumn)
        val absolutePath = cursor.getString(dataColumn)
        val url = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) MediaUriUtils.getRealPathUri(
            id,
            mimeType
        ) else absolutePath
        mimeType = if (mimeType.isNullOrEmpty()) MIME_TYPE_JPEG else mimeType

        // 修复某些机型获取 mimeType 返回 image/* 的问题
        if (mimeType.endsWith("image/*")) {
            mimeType = MediaUriUtils.getMimeTypeFromMediaUrl(absolutePath)
            if (config?.isGif == false) {
                if (mimeType.isGif()) {
                    return null
                }
            }
        }

        if (mimeType.endsWith("image/*")) {
            return null
        }

        if (config?.isWebp == false) {
            if (mimeType.startsWith(MIME_TYPE_WEBP)) {
                return null
            }
        }
        if (config?.isBmp == false) {
            if (mimeType.isBmp()) {
                return null
            }
        }
        if (config?.isHeic == false) {
            if (mimeType.isHeic()) {
                return null
            }
        }

        // 根据 orientation 调整宽高
        var width = cursor.getInt(widthColumn)
        var height = cursor.getInt(heightColumn)
        val orientation = cursor.getInt(orientationColumn)
        if (orientation == 90 || orientation == 270) {
            width = cursor.getInt(heightColumn)
            height = cursor.getInt(widthColumn)
        }
        val duration = cursor.getLong(durationColumn)
        val size = cursor.getLong(sizeColumn)
        val folderName = cursor.getString(folderNameColumn) ?: ""
        var fileName = cursor.getString(fileNameColumn) ?: ""
        val bucketId = cursor.getLong(bucketIdColumn)
        if (fileName.isEmpty()) {
            fileName = MimeTypeUtils.extractFileNameFromUrl(absolutePath)
        }
        if (size in 1..<KB) {
            // 过滤掉大小小于 1KB 的文件
            return null
        }
        if (mimeType.isVideo()) {
            if ((config?.fileMinDuration ?: 0) > 0 && duration < (config?.fileMinDuration ?: 0)) {
                // 如果设置了视频最小时长
                return null
            }
            if ((config?.fileMaxDuration ?: 0) > 0 && duration > (config?.fileMaxDuration ?: 0)) {
                // 如果设置了视频最大时长
                return null
            }
        }
        // 创建 LocalMedia 对象并设置属性
        return AlbumMediaFile(
            id = id,
            bucketId = bucketId,
            path = url,
            folderName = folderName.ifBlank { "未命名相册" },
            fileName = fileName,
            mimeType = mimeType,
            createDate = dateAdded,
            size = size,
            width = width,
            height = height,
            duration = duration,
            mediaType = mimeType,
            isChecked = false
        )
    }

    /**
     * 创建文件夹
     *
     * @param media 媒体文件对象
     * @param imageFolders 图片文件夹列表数据
     *
     * @return 返回对应的图片文件夹对象
     */
    private fun parseFolder(media: AlbumMediaFile, imageFolders: MutableList<AlbumMediaFolder>) {
        // 遍历图片文件夹列表
        for (folder in imageFolders) {
            // 如果文件夹名称与目标名称相同，则返回该文件夹
            val name = folder.folderName
            if (name.isBlank()) continue
            if (name == media.folderName) {
                folder.bucketId = media.bucketId
                folder.mediaList.add(media)
                folder.total++
                return
            }
        }
        // 如果未找到相同名称的文件夹，则创建一个新的文件夹对象并添加到列表中
        val newFolder = AlbumMediaFolder()
        newFolder.folderName = media.folderName
        newFolder.coverPath = media.path
        newFolder.coverMimeType = media.mimeType
        newFolder.bucketId = media.bucketId
        newFolder.mediaList.add(media)
        newFolder.total++
        imageFolders.add(newFolder)
    }

    /**
     * 是否是 Android Q 版本
     *
     * @return true 是，false 不是
     */
    private fun isAndroidQ(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    /**
     * 判断是否为content://路径
     *
     * @param path 路径
     *
     * @return true 是，false 不是
     */
    private fun isContentUri(path: String): Boolean {
        if (path.isBlank()) return false
        return path.startsWith("content://")
    }
}