package com.griffin.kp.model

import android.os.Parcel
import android.os.Parcelable

/**
 * 相册媒体文件夹
 *
 * @param bucketId 唯一标识 bucketId
 * @param folderName 文件夹名称
 * @param coverPath 封面路径
 * @param coverMimeType 封面MIME类型
 * @param total 文件数量
 * @param mediaList 文件列表
 * @param isChecked 是否选中
 */
data class AlbumMediaFolder(
    var bucketId: Long = -1,
    var folderName: String = "",
    var coverPath: String = "",
    var coverMimeType: String = "",
    var total: Int = 0,
    var mediaList: MutableList<AlbumMediaFile> = mutableListOf(),
    var isChecked: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        mutableListOf<AlbumMediaFile>().apply {
            parcel.readList(this, AlbumMediaFile::class.java.classLoader)
        },
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(bucketId)
        parcel.writeString(folderName)
        parcel.writeString(coverPath)
        parcel.writeString(coverMimeType)
        parcel.writeInt(total)
        parcel.writeList(mediaList)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumMediaFolder> {
        override fun createFromParcel(parcel: Parcel): AlbumMediaFolder {
            return AlbumMediaFolder(parcel)
        }

        override fun newArray(size: Int): Array<AlbumMediaFolder?> {
            return arrayOfNulls(size)
        }
    }
}