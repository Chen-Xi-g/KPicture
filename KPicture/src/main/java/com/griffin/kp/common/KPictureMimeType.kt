package com.griffin.kp.common

/**
 * 图片选择器MimeType常量
 *
 * @param mimeType 具体的MimeType
 */
sealed class KPictureMimeType(val mimeType: String) {

    /**
     * 图片
     */
    data object IMAGE : KPictureMimeType("image/*")

    /**
     * 视频
     */
    data object VIDEO : KPictureMimeType("video/*")

    /**
     * 所有
     */
    data object ALL : KPictureMimeType("image/*,video/*")

}