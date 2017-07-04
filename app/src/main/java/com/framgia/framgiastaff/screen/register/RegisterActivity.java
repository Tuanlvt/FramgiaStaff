package com.framgia.framgiastaff.screen.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.framgia.framgiastaff.R;
import com.framgia.framgiastaff.data.source.remote.EmployeeRepository;
import com.framgia.framgiastaff.data.source.remote.TypeRepository;
import com.framgia.framgiastaff.databinding.ActivityRegisterBinding;
import com.framgia.framgiastaff.screen.BaseActivity;
import com.framgia.framgiastaff.widgets.dialog.DialogManager;
import com.framgia.framgiastaff.widgets.dialog.DialogManagerInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * Register Screen.
 */
public class RegisterActivity extends BaseActivity {

    private RegisterContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogManagerInterface dialogManagerInterface = new DialogManager(this);

        List<String> mType = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mType);
        mViewModel = new RegisterViewModel(getApplicationContext(), dialogManagerInterface,
                arrayAdapter);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        TypeRepository typeRepository = new TypeRepository();
        RegisterContract.Presenter presenter =
                new RegisterPresenter(mViewModel, employeeRepository, typeRepository);
        mViewModel.setPresenter(presenter);

        ActivityRegisterBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewModel((RegisterViewModel) mViewModel);
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
