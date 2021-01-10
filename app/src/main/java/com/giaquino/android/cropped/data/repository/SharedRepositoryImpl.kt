package com.giaquino.android.cropped.data.repository

import javax.inject.Inject

class SharedRepositoryImpl @Inject constructor() : SharedRepository {

    override var isLoggedIn = false

    override var token: String? = null
}