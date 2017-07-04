package com.framgia.framgiastaff.screen.register;

import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.data.model.Type;
import com.framgia.framgiastaff.screen.BasePresenter;
import com.framgia.framgiastaff.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface RegisterContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onRegisterSuccess();

        void onRegisterError(Exception exception);

        void onInputIDError();

        void onInputEmailError();

        void onInputDateWorkingError();

        void onGetListTypeSuccess(List<Type> types);

        void onGetListTypeError(Exception exception);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onGetListType();

        void onRegister(Employee employee);

        boolean validateDataInput(Employee employee);
    }
}
