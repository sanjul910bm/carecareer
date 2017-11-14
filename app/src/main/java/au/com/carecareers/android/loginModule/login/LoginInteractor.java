package au.com.carecareers.android.loginModule.login;

import android.util.Log;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.ILoginInteractor {
    private static final String TAG = "LoginInteractor";

    @Inject
    public LoginInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public void login(LoginRequest request) {
        Log.d(TAG, "login: ");
    }
}
