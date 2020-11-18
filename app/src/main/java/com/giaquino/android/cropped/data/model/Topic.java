package com.giaquino.android.cropped.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topic {

    @SerializedName("id")
    public final String id;

    @SerializedName("slug")
    public final String slug;

    @SerializedName("title")
    public final String title;

    @SerializedName("description")
    public final String description;

    @SerializedName("total_photos")
    public final int totalPhotos;

    @SerializedName("owners")
    public final List<User> owners;

    @SerializedName("top_contributors")
    public final List<User> contributors;

    @SerializedName("cover_photo")
    public final Photo coverPhoto;

    public Topic(String id, String slug, String title, String description, int totalPhotos, List<User> owners, List<User> contributors, Photo coverPhoto) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.totalPhotos = totalPhotos;
        this.owners = owners;
        this.contributors = contributors;
        this.coverPhoto = coverPhoto;
    }
}
