package com.framgia.framgiastaff.screen.main;

import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.data.source.remote.EmployeeRepository;
import com.framgia.framgiastaff.data.source.remote.api.error.BaseException;
import com.framgia.framgiastaff.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();

    private final MainContract.ViewModel mViewModel;
    private final EmployeeRepository mEmployeeRepository;
    private final CompositeSubscription mCompositeSubscription;

    MainPresenter(MainContract.ViewModel viewModel, EmployeeRepository employeeRepository) {
        mViewModel = viewModel;
        mEmployeeRepository = employeeRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        getListEmployee();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getListEmployee() {
        Subscription subscription = mEmployeeRepository.getListEmployee()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Employee>>() {
                    @Override
                    public void call(List<Employee> employees) {
                        mViewModel.onGetListEmployeeSuccess(employees);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListEmployeeError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
