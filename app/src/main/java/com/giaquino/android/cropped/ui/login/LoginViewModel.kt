package com.giaquino.android.cropped.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.giaquino.android.cropped.data.api.request.TokenRequest
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Token
import com.giaquino.android.cropped.data.repository.SharedRepository
import com.giaquino.android.cropped.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val sharedRepository: SharedRepository) : ViewModel() {

    private val _token = MediatorLiveData<Resource<Token>>()
    val token: LiveData<Resource<Token>> = _token

    fun isLoggedIn() : Boolean {
        return sharedRepository.getUnsplashToken() != null
    }

    fun authorize(code: String) {
        val source: LiveData<Resource<Token>> = LiveDataReactiveStreams
                .fromPublisher(userRepository.authorize(TokenRequest(code = code)).toFlowable())

        _token.apply {
            postValue(Resource.loading(null))
            addSource(source) {
                postValue(it)
                removeSource(source)
            }
        }
    }
}