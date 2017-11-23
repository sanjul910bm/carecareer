package au.com.carecareers.android.profileModule.profileSetup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.customViews.EbImageHelperFragment;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.model.CandidateModel;
import au.com.carecareers.android.profileModule.preferredLocation.PreferredLocationActivity;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupModule;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileSetupActivity extends BaseActivity implements ProfileSetupContract.IProfileSetupView {
    @Inject
    Gson gson;
    @Inject
    ProfileSetupContract.IProfileSetupPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.civ_profile_image)
    CircleImageView civProfile;
    @BindView(R.id.tv_preferred_location)
    TextView tvPreferredLocation;

    private CandidateModel candidateModel;
    private EbImageHelperFragment ebImageHelperFragment;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ProfileSetupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_profile_setup;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideProfileSetupSubComponent(new ProfileSetupModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.candidateModel = new CandidateModel();
        initImageHelperFragment();
    }

    private void initImageHelperFragment() {
        ebImageHelperFragment = EbImageHelperFragment.newInstance(false, false);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(ebImageHelperFragment, EbImageHelperFragment.TAG);
        transaction.commit();
        ebImageHelperFragment.setListener(new EbImageHelperFragment.EbImageHelperListener() {
            @Override
            public void onImageSuccess(String imagePath) {
                Log.d("path", "onImageSuccess: " + imagePath);
                Picasso.with(ProfileSetupActivity.this)
                        .load(new File(imagePath))
                        .resize(200, 200)
                        .centerCrop()
                        .into(civProfile);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == AppContract.RequestCode.PREFERRED_LOCATION) {
                String extra = data.getStringExtra(AppContract.Extras.DATA);
                CandidateModel candidateModel = gson.fromJson(extra, CandidateModel.class);
                this.candidateModel.address = candidateModel.address;
                if (this.candidateModel.address != null) {
                    setPreferredLocation(this.candidateModel.address.address);
                } else {
                    setPreferredLocation("");
                }
            }
        }
    }

    @OnClick(R.id.tv_upload_photo)
    public void uploadPhotoClicked() {
        ebImageHelperFragment.showChooserDialog();
    }

    @OnClick(R.id.ll_preferred_location_main)
    public void preferredLocationClicked() {
        navigateToPreferredLocation();
    }

    @OnClick(R.id.ll_location_area)
    public void locationAreaClicked() {

    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_personal_details));
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public void setPreferredLocation(String location) {
        tvPreferredLocation.setText(location);
    }

    @Override
    public void setLocationArea(String locationArea) {

    }

    @Override
    public void setProfessionRole(String professionRole) {

    }

    @Override
    public void setWorkType(String workType) {

    }

    @Override
    public void navigateToPreferredLocation() {
        PreferredLocationActivity.startForResult(this, gson.toJson(candidateModel));
    }
}