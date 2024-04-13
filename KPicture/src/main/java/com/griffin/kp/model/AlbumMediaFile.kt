package com.griffin.kp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.IntDef

/**
 * 媒体文件
 *
 * @param id 唯一标识
 * @param bucketId 唯一标识 bucketId
 * @param path 文件路径
 * @param folderName 文件夹名称
 * @param fileName 文件名称
 * @param mimeType 文件 MIME 类型
 * @param createDate 添加日期
 * @param size 大小
 * @param width 宽度
 * @param height 高度
 * @param duration 时长
 * @param mediaType 媒体类型
 * @param isChecked 是否选中
 */
data class AlbumMediaFile(
    var id: Long = -1,
    var bucketId: Long = -1,
    var path: String = "",
    var folderName: String = "",
    var fileName: String = "",
    var mimeType: String = "",
    var createDate: Long = 0,
    var size: Long = 0,
    var width: Int = 0,
    var height: Int = 0,
    var duration: Long = 0,
    var mediaType: String = "",
    var isChecked: Boolean = false
) : Parcelable, Comparable<AlbumMediaFile> {

    override fun compareTo(other: AlbumMediaFile): Int {
        val time = other.createDate - createDate
        return when {
            time > Int.MAX_VALUE -> Int.MAX_VALUE
            time < -Int.MAX_VALUE -> -Int.MAX_VALUE
            else -> time.toInt()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is AlbumMediaFile) {
            val inPath = other.path
            if (path != null && inPath != null) {
                return path == inPath
            }
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return path?.hashCode() ?: super.hashCode()
    }

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeLong(bucketId)
        dest.writeString(path)
        dest.writeString(folderName)
        dest.writeString(fileName)
        dest.writeString(mimeType)
        dest.writeLong(createDate)
        dest.writeLong(size)
        dest.writeInt(width)
        dest.writeInt(height)
        dest.writeLong(duration)
        dest.writeString(mediaType)
        dest.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumMediaFile> {

        override fun createFromParcel(parcel: Parcel): AlbumMediaFile {
            return AlbumMediaFile(parcel)
        }

        override fun newArray(size: Int): Array<AlbumMediaFile?> {
            return arrayOfNulls(size)
        }
    }

}
