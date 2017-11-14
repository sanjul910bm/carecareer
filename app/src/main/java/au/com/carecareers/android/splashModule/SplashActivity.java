package au.com.carecareers.android.splashModule;

import android.os.Bundle;
import android.os.Handler;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.start(SplashActivity.this);
            }
        }, 2000);
    }
}
