package com.framgia.framgiastaff.screen.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.screen.BaseRecyclerViewAdapter;
import com.framgia.framgiastaff.screen.employeedetail.EmployeedetailActivity;
import java.util.List;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Employee> {

    private MainContract.Presenter mPresenter;
    private final Context mContext;
    private MainAdapter mAdapter;

    public MainViewModel(Context context, MainAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
        mAdapter.setListener(this);
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Employee item) {
        mContext.startActivity(
                new Intent(EmployeedetailActivity.getEmployeeIntent(mContext, item)).setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onGetListEmployeeSuccess(List<Employee> employees) {
        mAdapter.updateData(employees);
    }

    @Override
    public void onGetListEmployeeError(Exception exception) {
        //Todo show message error
    }

    public MainAdapter getAdapter() {
        return mAdapter;
    }
}
