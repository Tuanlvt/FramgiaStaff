package com.framgia.framgiastaff.screen.employeedetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.framgiastaff.R;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.databinding.ActivityEmployeedetailBinding;
import com.framgia.framgiastaff.screen.BaseActivity;

/**
 * Employeedetail Screen.
 */
public class EmployeedetailActivity extends BaseActivity {

    private static final String EXTRA_EMPLOYEE = "EXTRA_EMPLOYEE";

    private EmployeedetailContract.ViewModel mViewModel;

    public static Intent getEmployeeIntent(Context context, Employee employee) {
        Intent intent = new Intent(context, EmployeedetailActivity.class);
        intent.putExtra(EXTRA_EMPLOYEE, employee);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Employee employee = (Employee) getIntent().getSerializableExtra(EXTRA_EMPLOYEE);
        mViewModel = new EmployeedetailViewModel(employee);

        EmployeedetailContract.Presenter presenter = new EmployeedetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityEmployeedetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_employeedetail);
        binding.setViewModel((EmployeedetailViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
