package com.griffin.kp.model

import android.os.Parcel
import android.os.Parcelable

const val CAMERA_ID = -2L

/**
 * 图片媒体
 *
 * @param id 唯一标识
 * @param path 路径
 * @param duration 时长
 * @param width 宽度
 * @param height 高度
 * @param fileName 文件名
 * @param check 是否选中
 */
data class KPictureMedia(
    val id: Long = 0,
    val path: String = "",
    val duration: Long = 0,
    val width: Int = 0,
    val height: Int = 0,
    val fileName: String = "",
    val check: Boolean = false
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(path)
        dest.writeLong(duration)
        dest.writeInt(width)
        dest.writeInt(height)
        dest.writeString(fileName)
        dest.writeByte(if (check) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<KPictureMedia> {
        override fun createFromParcel(parcel: Parcel): KPictureMedia {
            return KPictureMedia(parcel)
        }

        override fun newArray(size: Int): Array<KPictureMedia?> {
            return arrayOfNulls(size)
        }
    }
}