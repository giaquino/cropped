package com.giaquino.android.cropped.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("id") val id: String,
        @SerializedName("color") val color: String,
        @SerializedName("description") val description: String,
        @SerializedName("urls") val urls: Urls) {

    class Urls(@SerializedName("raw") val raw: String,
               @SerializedName("regular") val regular: String,
               @SerializedName("small") val small: String,
               @SerializedName("thumb") val thumb: String)
}