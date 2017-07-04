package com.framgia.framgiastaff.screen.main;

import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.screen.BasePresenter;
import com.framgia.framgiastaff.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListEmployeeSuccess(List<Employee> employees);

        void onGetListEmployeeError(Exception exception);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListEmployee();
    }
}
