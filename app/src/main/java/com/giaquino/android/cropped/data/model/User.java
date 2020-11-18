package com.giaquino.android.cropped.data.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    public final String id;

    @SerializedName("username")
    public final String username;

    @SerializedName("name")
    public final String name;

    @SerializedName("first_name")
    public final String firstName;

    @Nullable
    @SerializedName("last_name")
    public final String lastName;

    @SerializedName("bio")
    public final String bio;

    @SerializedName("profile_image")
    public final ProfileImage profileImage;

    public User(String id, String username, String name, String firstName, String lastName, String bio, ProfileImage profileImage) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profileImage = profileImage;
    }

    public static class ProfileImage {

        @SerializedName("small")
        public final String small;

        @SerializedName("medium")
        public final String medium;

        @SerializedName("large")
        public final String large;

        public ProfileImage(String small, String medium, String large) {
            this.small = small;
            this.medium = medium;
            this.large = large;
        }
    }
}
