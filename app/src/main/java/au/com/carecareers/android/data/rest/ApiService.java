package au.com.carecareers.android.data.rest;


import javax.inject.Singleton;

import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@Singleton
public interface ApiService {

    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept:application/json, text/plain, */*",
    })
    @FormUrlEncoded
    @POST(UrlContract.AUTHORIZE)
    Observable<AuthenticationModel.AuthenticationResponse> auth(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                                                @Field(UrlContract.Keys.GRANT_TYPE) String grantType);


}
