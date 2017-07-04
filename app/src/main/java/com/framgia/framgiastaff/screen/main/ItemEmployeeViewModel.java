package com.framgia.framgiastaff.screen.main;

import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 7/3/17.
 */

public class ItemEmployeeViewModel {

    private final Employee mEmployee;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> mListener;

    public ItemEmployeeViewModel(Employee employee,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> listener) {
        mEmployee = employee;
        mListener = listener;
    }

    public String getId() {
        return mEmployee.getId();
    }

    public String getEmail() {
        return mEmployee.getEmail();
    }

    public void onItemClick() {
        if (mListener == null) {
            return;
        }
        mListener.onItemRecyclerViewClick(mEmployee);
    }
}
