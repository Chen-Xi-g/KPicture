package com.griffin.kp.utils

object MimeTypeUtils {

    private const val MIME_TYPE_IMAGE = "image"
    private const val MIME_TYPE_VIDEO = "video"

    /*-----------------图片类型------------------------*/

    const val MIME_TYPE_JPEG = "image/jpeg"
    const val MIME_TYPE_PNG = "image/png"
    const val MIME_TYPE_GIF = "image/gif"
    const val MIME_TYPE_WEBP = "image/webp"
    const val MIME_TYPE_BMP = "image/bmp"
    const val MIME_TYPE_X_MS_BMP = "image/x-ms-bmp"
    const val MIME_TYPE_WAP_BMP = "image/vnd.wap.wbmp"
    const val MIME_TYPE_HEIC = "image/heic"
    const val MIME_TYPE_3GP = "video/3gpp"
    const val MIME_TYPE_MP4 = "video/mp4"
    const val MIME_TYPE_MPEG = "video/mpeg"
    const val MIME_TYPE_AVI = "video/avi"

    /**
     * 判断当前的miniType是否是图片
     */
    fun String?.isImage(): Boolean {
        return !isNullOrEmpty() && this.startsWith(MIME_TYPE_IMAGE)
    }

    /**
     * 判断当前的miniType是否是视频
     */
    fun String?.isVideo(): Boolean {
        return !isNullOrEmpty() && this.startsWith(MIME_TYPE_VIDEO)
    }

    /**
     * 判断是否为 GIF 类型的 MIME 类型
     */
    fun String?.isGif(): Boolean {
        // 判断 MIME 类型是否为 GIF 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_GIF, ignoreCase = true))
    }

    /**
     * 判断是否为 Webp 类型的 MIME 类型
     */
    fun String?.isWebp(): Boolean {
        // 判断 MIME 类型是否为 Webp 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_WEBP, ignoreCase = true))
    }

    /**
     * 判断是否为 BMP 类型的 MIME 类型
     */
    fun String?.isBmp(): Boolean {
        // 判断 MIME 类型是否为 BMP 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_BMP, ignoreCase = true))
    }

    /**
     * 判断是否为 XmsBmp 类型的 MIME 类型
     */
    fun String?.isXmsBmp(): Boolean {
        // 判断 MIME 类型是否为 XmsBmp 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_X_MS_BMP, ignoreCase = true))
    }

    /**
     * 判断是否为 WapBmp 类型的 MIME 类型
     */
    fun String?.isWapBmp(): Boolean {
        // 判断 MIME 类型是否为 WapBmp 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_WAP_BMP, ignoreCase = true))
    }

    /**
     * 判断是否为 Heic 类型的 MIME 类型
     */
    fun String?.isHeic(): Boolean {
        // 判断 MIME 类型是否为 Heic 类型
        return !isNullOrEmpty() && (this.equals(MIME_TYPE_HEIC, ignoreCase = true))
    }

    /**
     * 从 URL 中提取文件名
     *
     * @param path URL 路径
     * @return 返回文件名
     */
    fun extractFileNameFromUrl(path: String): String {
        var result = ""
        try {
            // 找到路径中最后一个斜杠的索引
            val lastIndexOf = path.lastIndexOf("/")
            if (lastIndexOf != -1) {
                // 截取路径中最后一个斜杠后的部分作为文件名
                result = path.substring(lastIndexOf + 1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}