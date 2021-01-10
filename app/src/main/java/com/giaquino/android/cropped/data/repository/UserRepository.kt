package com.giaquino.android.cropped.data.repository

import com.giaquino.android.cropped.data.api.request.TokenRequest
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Token
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun authorize(request: TokenRequest): Single<Resource<Token>>
}