package com.giaquino.android.cropped.data.model

import com.google.gson.annotations.SerializedName

class Topic(@SerializedName("id") val id: String,
            @SerializedName("slug") val slug: String,
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: String,
            @SerializedName("total_photos") val totalPhotos: Int,
            @SerializedName("owners") val owners: List<User>,
            @SerializedName("top_contributors") val contributors: List<User>,
            @SerializedName("cover_photo") val coverPhoto: Photo)