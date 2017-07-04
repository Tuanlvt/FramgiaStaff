package com.framgia.framgiastaff.screen.employeedetail;

import com.framgia.framgiastaff.data.model.Employee;

/**
 * Exposes the data to be used in the Employeedetail screen.
 */

public class EmployeedetailViewModel implements EmployeedetailContract.ViewModel {

    private EmployeedetailContract.Presenter mPresenter;
    private final Employee mEmployee;

    public EmployeedetailViewModel(Employee employee) {
        mEmployee = employee;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(EmployeedetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getID() {
        return mEmployee.getId();
    }

    public String getEmail() {
        return mEmployee.getEmail();
    }

    public String getSex() {
        return mEmployee.getSex();
    }

    public String getDateWorking() {
        return mEmployee.getDateWorking();
    }

    public String getType() {
        return mEmployee.getType();
    }

    public String getLocation() {
        return mEmployee.getLocation();
    }
}
