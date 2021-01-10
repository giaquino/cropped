package com.giaquino.android.cropped.data.model

import com.google.gson.annotations.SerializedName

class User(@SerializedName("id") val id: String,
           @SerializedName("username") val username: String,
           @SerializedName("name") val name: String,
           @SerializedName("first_name") val firstName: String,
           @SerializedName("last_name") val lastName: String?,
           @SerializedName("bio") val bio: String,
           @SerializedName("profile_image") val profileImage: ProfileImage) {

    class ProfileImage(@SerializedName("small") val small: String,
                       @SerializedName("medium") val medium: String,
                       @SerializedName("large") val large: String)
}