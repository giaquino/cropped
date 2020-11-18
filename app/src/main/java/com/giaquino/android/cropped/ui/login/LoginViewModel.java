package com.giaquino.android.cropped.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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

    public static class Factory implements ViewModelProvider.Factory {

        private UserRepository userRepository;
        private SharedRepository sharedRepository;

        public Factory(UserRepository userRepository, SharedRepository sharedRepository) {
            this.userRepository = userRepository;
            this.sharedRepository = sharedRepository;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(userRepository, sharedRepository);
        }
    }
}
