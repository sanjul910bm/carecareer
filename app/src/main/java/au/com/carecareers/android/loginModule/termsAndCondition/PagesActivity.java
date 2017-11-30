package au.com.carecareers.android.loginModule.termsAndCondition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.termsAndCondition.injection.TermsAndConditionsModule;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import butterknife.BindView;

/**
 * Created by Sanjay on 11/22/2017.
 */


public class PagesActivity extends BaseActivity implements
        TermsAndConditionsContract.ITermsAndConditionsView {
    @BindView(R.id.terms_and_conditions_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.loading_progress)
    ProgressBar loadingProgressBar;

    @BindView(R.id.webview_terms_and_conditions)
    WebView wvTermsAndConditions;
    @Inject
    TermsAndConditionPresenter presenter;
    int pageFlag;

    public static void start(Context context, int flag) {
        Intent intent = new Intent();
        intent.setClass(context, PagesActivity.class);
        intent.putExtra(AppContract.Page.PAGE_FLAG, flag);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_terms_and_conditions;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideTermsAndConditionsSubComponent(new TermsAndConditionsModule()).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pageFlag = extras.getInt(AppContract.Page.PAGE_FLAG);
        }

        String type = "page";

        if (pageFlag == AppContract.Page.TERMS_AND_CONDITIONS) {
            tvTitle.setText(getResources().getText(R.string.tv_settings_terms_and_conditions));
            String idOrSlug = "terms-of-access-and-use";
            presenter.termsAndCondition(type, idOrSlug);
        } else if(pageFlag == AppContract.Page.PRIVACY_POLICY){
            tvTitle.setText(getResources().getText(R.string.tv_settings_privacy_policy));
            String idOrSlug = "privacy-policy";
            presenter.termsAndCondition(type, idOrSlug);
        }else if(pageFlag == AppContract.Page.STARTING_OUT){
            tvTitle.setText(getResources().getText(R.string.tv_title_starting_out));
            String idOrSlug = "starting-out";
            presenter.termsAndCondition(type, idOrSlug);
        }
    }

    @Override
    public void naviagteToTermsAndConditonsWebView(TermsAndConditionsModel.TermsAndConditionsRespones termsAndConditionsRespones) {
        loadingProgressBar.setVisibility(View.GONE);
        wvTermsAndConditions.setWebViewClient(new WebViewClient());
        wvTermsAndConditions.setScrollbarFadingEnabled(false);
        wvTermsAndConditions.getSettings().setBuiltInZoomControls(true);
        wvTermsAndConditions.getSettings().setDisplayZoomControls(false);
        wvTermsAndConditions.getSettings().setJavaScriptEnabled(true);

        String baseUrl = "file:///android_asset/";

        wvTermsAndConditions.loadDataWithBaseURL(baseUrl, termsAndConditionsRespones.getContent(), "text/html", "UTF-8", null);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                transitionBackPressed();
                break;
        }
        return true;
    }
}