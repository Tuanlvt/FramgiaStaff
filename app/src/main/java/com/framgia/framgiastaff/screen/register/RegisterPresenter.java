package com.framgia.framgiastaff.screen.register;

import android.text.TextUtils;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.data.model.Type;
import com.framgia.framgiastaff.data.source.remote.EmployeeRepository;
import com.framgia.framgiastaff.data.source.remote.TypeRepository;
import com.framgia.framgiastaff.data.source.remote.api.error.BaseException;
import com.framgia.framgiastaff.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link RegisterActivity}), retrieves the data and updates
 * the UI as required.
 */
final class RegisterPresenter implements RegisterContract.Presenter {
    private static final String TAG = RegisterPresenter.class.getName();

    private final RegisterContract.ViewModel mViewModel;
    private final EmployeeRepository mEmployeeRepository;
    private final TypeRepository mTypeRepository;
    private final CompositeSubscription mCompositeSubscription;

    RegisterPresenter(RegisterContract.ViewModel viewModel, EmployeeRepository employeeRepository,
            TypeRepository typeRepository) {
        mViewModel = viewModel;
        mEmployeeRepository = employeeRepository;
        mTypeRepository = typeRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        onGetListType();
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void onGetListType() {
        Subscription subscription = mTypeRepository.getListType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Type>>() {
                    @Override
                    public void call(List<Type> types) {
                        mViewModel.onGetListTypeSuccess(types);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListTypeError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onRegister(Employee employee) {
        if (!validateDataInput(employee)) {
            return;
        }
        Subscription subscription = mEmployeeRepository.register(employee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Employee>() {
                    @Override
                    public void call(Employee employee) {
                        mViewModel.onRegisterSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onRegisterError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public boolean validateDataInput(Employee employee) {
        boolean isValid = true;
        if (TextUtils.isEmpty(employee.getId())) {
            isValid = false;
            mViewModel.onInputIDError();
        }
        if (TextUtils.isEmpty(employee.getEmail())) {
            isValid = false;
            mViewModel.onInputEmailError();
        }
        if (TextUtils.isEmpty(employee.getDateWorking())) {
            isValid = false;
            mViewModel.onInputDateWorkingError();
        }
        return isValid;
    }
}
