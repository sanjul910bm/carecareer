package au.com.carecareers.android.loginModule.changePassword;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.Completable;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ChangePasswordContract {
    public interface IChangePasswordView extends IBaseView {

        void navigateToLoginActivity();
    }

    public interface IChangePresenter extends IBasePresenter<IChangePasswordView, IChangeInteractor> {


        boolean validateFields(ChangePasswordModel.ChangePasswordRequest newPassword);

        void changePassword(ChangePasswordModel.ChangePasswordRequest newPassword);
    }

    public interface IChangeInteractor extends IBaseInteractor {
        Completable changePassword(ChangePasswordModel.ChangePasswordRequest changePasswordRequest);
    }
}
