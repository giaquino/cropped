package com.giaquino.android.cropped.data.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    public final String id;

    @SerializedName("color")
    public final String color;

    @SerializedName("description")
    public final String description;

    @SerializedName("urls")
    public final PhotoUrls urls;

    public Photo(String id, String color, String description, PhotoUrls urls) {
        this.id = id;
        this.color = color;
        this.description = description;
        this.urls = urls;
    }

    public static class PhotoUrls {

        @SerializedName("raw")
        public final String raw;

        @SerializedName("regular")
        public final String regular;

        @SerializedName("small")
        public final String small;

        @SerializedName("thumb")
        public final String thumb;

        public PhotoUrls(String raw, String regular, String small, String thumb) {
            this.raw = raw;
            this.regular = regular;
            this.small = small;
            this.thumb = thumb;
        }
    }
}
