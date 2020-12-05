package com.giaquino.android.cropped.ui.login;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.giaquino.android.cropped.common.Constant;
import com.giaquino.android.cropped.data.api.request.AccessTokenRequest;
import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.AccessToken;
import com.giaquino.android.cropped.data.repository.SharedRepository;
import com.giaquino.android.cropped.data.repository.UserRepository;

public class LoginViewModel extends ViewModel {

    private UserRepository userRepository;
    private SharedRepository sharedRepository;
    private MediatorLiveData<Resource<AccessToken>> accessToken = new MediatorLiveData<>();


    @ViewModelInject
    public LoginViewModel(UserRepository userRepository, SharedRepository sharedRepository) {
        this.userRepository = userRepository;
        this.sharedRepository = sharedRepository;
    }

    public boolean isLoggedIn() {
        return sharedRepository.isLoggedIn();
    }

    public LiveData<Resource<AccessToken>> accessToken() {
        return accessToken;
    }

    public void authorize(String code) {
        AccessTokenRequest request = new AccessTokenRequest(
                Constant.UNSPLASH_CLIENT_ID,
                Constant.UNSPLASH_CLIENT_SECRET,
                Constant.UNSPLASH_REDIRECT_URI,
                code);

        LiveData<Resource<AccessToken>> liveData = LiveDataReactiveStreams.fromPublisher(
                userRepository.authorize(request).toFlowable());

        accessToken.setValue(Resource.loading(null));

        accessToken.addSource(liveData, resource -> {
            accessToken.setValue(resource);
            accessToken.removeSource(liveData);
        });
    }
}
