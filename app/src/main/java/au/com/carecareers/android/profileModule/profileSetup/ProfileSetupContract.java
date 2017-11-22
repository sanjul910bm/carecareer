package au.com.carecareers.android.profileModule.profileSetup;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nischal Manandhar on 17/11/2017.
 */

public class ProfileSetupContract {
    public interface IProfileSetupView extends IBaseView {
        void navigateToPreferredLocation();
    }

    public interface IProfileSetupPresenter extends IBasePresenter<IProfileSetupView, IProfileSetupInteractor> {

    }

    public interface IProfileSetupInteractor extends IBaseInteractor {

    }
}
