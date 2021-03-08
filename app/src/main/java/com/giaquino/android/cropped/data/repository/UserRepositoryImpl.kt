package com.giaquino.android.cropped.data.repository

import com.giaquino.android.cropped.data.api.UnsplashAuthApi
import com.giaquino.android.cropped.data.api.request.TokenRequest
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Token
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UnsplashAuthApi,
                                             private val sharedRepository: SharedRepository) : UserRepository {

    override fun authorize(request: TokenRequest): Single<Resource<Token>> {
        return api.getAccessToken(request)
                .map { data: Token -> Resource.success(data) }
                .onErrorReturn { error: Throwable -> Resource.failure(error) }
                .doOnSuccess { resource: Resource<Token> ->
                    resource.isSuccess {
                        runBlocking { sharedRepository.setUnsplashToken(it.accessToken) }
                    }
                }
    }
}