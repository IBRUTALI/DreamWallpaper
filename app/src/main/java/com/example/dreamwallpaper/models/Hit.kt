package com.example.dreamwallpaper.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Hit(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
): Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(collections)
        parcel.writeInt(comments)
        parcel.writeInt(downloads)
        parcel.writeInt(id)
        parcel.writeInt(imageHeight)
        parcel.writeInt(imageSize)
        parcel.writeInt(imageWidth)
        parcel.writeString(largeImageURL)
        parcel.writeInt(likes)
        parcel.writeString(pageURL)
        parcel.writeInt(previewHeight)
        parcel.writeString(previewURL)
        parcel.writeInt(previewWidth)
        parcel.writeString(tags)
        parcel.writeString(type)
        parcel.writeString(user)
        parcel.writeString(userImageURL)
        parcel.writeInt(user_id)
        parcel.writeInt(views)
        parcel.writeInt(webformatHeight)
        parcel.writeString(webformatURL)
        parcel.writeInt(webformatWidth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hit> {
        override fun createFromParcel(parcel: Parcel): Hit {
            return Hit(parcel)
        }

        override fun newArray(size: Int): Array<Hit?> {
            return arrayOfNulls(size)
        }
    }
}