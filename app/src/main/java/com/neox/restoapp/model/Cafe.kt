package com.neox.restoapp.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cafe(
    @SerializedName("status")
    @Expose
    val status: Int,

    @SerializedName("values")
    @Expose
    val values: List<Value>
) : Parcelable