package com.giaquino.android.cropped.data.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

public class Resource<T> {

    public enum Status {
        SUCCESS, FAILURE, LOADING
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(data, null, Status.SUCCESS);
    }

    public static <T> Resource<T> failure(@NonNull Throwable error) {
        return new Resource<>(null, error, Status.FAILURE);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(data, null, Status.LOADING);
    }

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    @NonNull
    public final Status status;

    private Resource(@Nullable T data, @Nullable Throwable error, @NonNull Status status) {
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public void isSuccess(Consumer<T> consumer) {
        if (status == Status.SUCCESS) consumer.accept(data);
    }

    public void isFailure(Consumer<Throwable> consumer) {
        if (status == Status.FAILURE) consumer.accept(error);
    }

    public void isLoading(Consumer<T> consumer) {
        if (status == Status.LOADING) consumer.accept(data);
    }
}
