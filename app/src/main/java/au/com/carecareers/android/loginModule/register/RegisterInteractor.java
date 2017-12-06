package au.com.carecareers.android.loginModule.register;

import android.text.TextUtils;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Dell on 11/16/2017.
 */

public class RegisterInteractor extends BaseInteractor implements RegisterContract.IRegisterInteractor {
    @Inject
    public RegisterInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<Boolean> isPreferenceCleared() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return TextUtils.isEmpty(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY));
            }
        });
    }

    @Override
    public Observable<TaxonomyModel.TaxonomyResponse> getStates() {
        return getApiService().getStates(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY));
    }

    @Override
    public Observable<RegisterModel.RegisterResponse> register(RegisterModel.RegisterRequest request) {
        return getApiService().register(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), request);
    }

    @Override
    public void saveRegisterResponse(RegisterModel.RegisterResponse registerResponse) {

    }
}
