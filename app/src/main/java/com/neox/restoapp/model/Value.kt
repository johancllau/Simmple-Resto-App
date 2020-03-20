package com.neox.restoapp.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Value(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("space_address")
    @Expose
    val spaceAddress: String,

    @SerializedName("space_description")
    @Expose
    val spaceDescription: String,

    @SerializedName("space_facilities")
    @Expose
    val spaceFacilities: String?,

    @SerializedName("space_hours")
    @Expose
    val spaceHours: String,

    @SerializedName("space_location")
    @Expose
    val spaceLocation: String,

    @SerializedName("space_name")
    @Expose
    val spaceName: String,

    @SerializedName("space_photo")
    @Expose
    val spacePhoto: String
) : Parcelable