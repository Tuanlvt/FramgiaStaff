package com.framgia.framgiastaff.screen.employeedetail;

import com.framgia.framgiastaff.screen.BasePresenter;
import com.framgia.framgiastaff.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface EmployeedetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
