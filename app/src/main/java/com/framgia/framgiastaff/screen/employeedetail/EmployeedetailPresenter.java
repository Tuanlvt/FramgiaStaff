package com.framgia.framgiastaff.screen.employeedetail;

/**
 * Listens to user actions from the UI ({@link EmployeedetailActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class EmployeedetailPresenter implements EmployeedetailContract.Presenter {
    private static final String TAG = EmployeedetailPresenter.class.getName();

    private final EmployeedetailContract.ViewModel mViewModel;

    public EmployeedetailPresenter(EmployeedetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
