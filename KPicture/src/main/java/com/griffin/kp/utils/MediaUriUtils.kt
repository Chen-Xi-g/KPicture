package com.griffin.kp.utils

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.griffin.kp.utils.MimeTypeUtils.MIME_TYPE_JPEG
import com.griffin.kp.utils.MimeTypeUtils.isImage
import com.griffin.kp.utils.MimeTypeUtils.isVideo
import java.io.File
import java.net.FileNameMap
import java.net.URLConnection


object MediaUriUtils {
    /**
     * 获取真实路径的 URI
     *
     * @param id 媒体文件的 ID
     * @param mimeType 媒体文件的 MIME 类型
     * @return 返回媒体文件的真实路径的 URI
     */
    fun getRealPathUri(id: Long, mimeType: String): String {
        val contentUri: Uri = when {
            mimeType.isImage() -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            mimeType.isVideo() -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            else -> MediaStore.Files.getContentUri("external")
        }
        return ContentUris.withAppendedId(contentUri, id).toString()
    }

    /**
     * 根据媒体文件路径获取 MIME 类型
     *
     * @param path 媒体文件的路径
     * @return 返回媒体文件的 MIME 类型
     */
    fun getMimeTypeFromMediaUrl(path: String): String {
        // 从文件路径中获取文件扩展名
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(path)
        // 根据文件扩展名获取 MIME 类型
        var mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
            fileExtension.lowercase()
        )
        // 如果获取的 MIME 类型为空，则尝试从文件对象中获取
        if (mimeType.isNullOrEmpty()) {
            mimeType = getMimeType(File(path))
        }
        // 如果获取的 MIME 类型仍为空，则默认返回 JPEG 类型
        return mimeType.ifEmpty { MIME_TYPE_JPEG }
    }

    /**
     * 根据文件获取 MIME 类型
     *
     * @param file 文件对象
     * @return 返回文件的 MIME 类型
     */
    private fun getMimeType(file: File): String {
        val fileNameMap: FileNameMap = URLConnection.getFileNameMap()
        return fileNameMap.getContentTypeFor(file.name)
    }

    /**
     * uri获取文件路径
     *
     * @param context 上下文
     * @param uri 文件的uri
     *
     * @return 返回文件的路径
     */
    fun getFilePathFromUri(context: Context, uri: Uri): String {
        val applicationContext = context.applicationContext
        if (DocumentsContract.isDocumentUri(context, uri)) {
            when {
                isExternalStorageDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":")
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            "${applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.path}/${split[1]}"
                        } else {
                            "${Environment.getExternalStorageDirectory()}/${split[1]}"
                        }
                    }
                }

                isDownloadsDocument(uri) -> {
                    val id = DocumentsContract.getDocumentId(uri)
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        id.toLong()
                    )
                    return getDataColumn(applicationContext, contentUri, null, null)
                }

                isMediaDocument(uri) -> {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":")
                    val type = split[0]
                    val contentUri = when (type) {
                        "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                        else -> MediaStore.Files.getContentUri("external")
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])
                    return getDataColumn(applicationContext, contentUri, selection, selectionArgs)
                }
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return if (isGooglePhotosUri(uri)) {
                uri.lastPathSegment ?: ""
            } else {
                getDataColumn(applicationContext, uri, null, null)
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path ?: ""
        }
        return ""
    }

    /**
     * 判断是否为外部存储文档
     *
     * @param uri 文件的uri
     * @return 返回是否为外部存储文档
     */
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * 判断是否为下载文档
     *
     * @param uri 文件的uri
     * @return 返回是否为下载文档
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * 判断是否为媒体文档
     *
     * @param uri 文件的uri
     * @return 返回是否为媒体文档
     */
    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * 判断是否为Google相册文档
     *
     * @param uri 文件的uri
     * @return 返回是否为Google相册文档
     */
    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

    /**
     * 获取数据列
     *
     * @param context 上下文
     * @param uri 文件的uri
     * @param selection 选择
     * @param selectionArgs 选择参数
     * @return 返回数据列
     */
    private fun getDataColumn(
        context: Context,
        uri: Uri,
        selection: String?,
        selectionArgs: Array<String>?
    ): String {
        val column = "_data"
        val projection = arrayOf(column)
        context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            ?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(columnIndex)
                }
            }
        return ""
    }
}