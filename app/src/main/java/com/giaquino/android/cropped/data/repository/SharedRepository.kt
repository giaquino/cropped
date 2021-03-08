package com.giaquino.android.cropped.data.repository

interface SharedRepository {

    fun getUnsplashToken() : String?

    fun setUnsplashToken(token : String)
}