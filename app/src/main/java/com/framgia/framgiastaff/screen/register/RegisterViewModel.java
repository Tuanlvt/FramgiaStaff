package com.framgia.framgiastaff.screen.register;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.framgiastaff.R;
import com.framgia.framgiastaff.data.model.Employee;
import com.framgia.framgiastaff.data.model.Type;
import com.framgia.framgiastaff.screen.main.MainActivity;
import com.framgia.framgiastaff.utils.Utils;
import com.framgia.framgiastaff.widgets.dialog.DialogManagerInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.framgia.framgiastaff.utils.Constant.DEFAULT_VALUE;
import static com.framgia.framgiastaff.utils.Utils.emailValidator;

/**
 * Exposes the data to be used in the Register screen.
 */

public class RegisterViewModel extends BaseObservable
        implements RegisterContract.ViewModel, DatePickerDialog.OnDateSetListener {

    private RegisterContract.Presenter mPresenter;

    private final Context mContext;
    private final Calendar mCalendar;
    private final DialogManagerInterface mDialogManager;
    private String mId;
    private String mIdError;
    private String mEmail;
    private String mEmailError;
    private String mDate;
    private String mDateError;
    private final ArrayAdapter<String> mAdapter;
    private final List<Type> mTypes;
    private int mSelectedTypePosition;

    public RegisterViewModel(@NonNull Context context, DialogManagerInterface dialogManager,
            ArrayAdapter<String> adapter) {
        mContext = context;
        mCalendar = Calendar.getInstance();
        mDialogManager = dialogManager;
        mTypes = new ArrayList<>();
        mDialogManager.dialogDatePicker(this);
        mAdapter = adapter;
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
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRegisterSuccess() {
        //Todo show Message
    }

    @Override
    public void onRegisterError(Exception exception) {
        //Todo show Message
    }

    @Override
    public void onInputIDError() {
        mIdError = mContext.getString(R.string.id_is_empty);
        notifyPropertyChanged(BR.idError);
    }

    @Override
    public void onInputEmailError() {
        mEmailError = mContext.getString(R.string.email_is_empty);
        notifyPropertyChanged(BR.emailError);
    }

    @Override
    public void onInputDateWorkingError() {
        mDateError = mContext.getString(R.string.please_select_working_date);
        notifyPropertyChanged(BR.dateError);
    }

    @Override
    public void onGetListTypeSuccess(List<Type> types) {
        if (types == null) {
            return;
        }
        mTypes.clear();
        mTypes.addAll(types);
        mAdapter.add(mContext.getString(R.string.select_type));
        for (Type type : types) {
            mAdapter.add(type.getType());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListTypeError(Exception exception) {
        //Todo show Message
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mDate = Utils.DateTimeUntils.convertDateToString(mCalendar.getTime());
        notifyPropertyChanged(BR.date);
        notifyPropertyChanged(BR.dateError);
    }

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Bindable
    public String getIdError() {
        return mIdError;
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Bindable
    public String getEmailError() {
        return mEmailError;
    }

    @Bindable
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    @Bindable
    public String getDateError() {
        return mDateError;
    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }

    public void setSelectedTypePosition(int selectedTypePosition) {
        mSelectedTypePosition = selectedTypePosition;
    }

    public int getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public void onClickRegister() {

        Employee registerRequest = new Employee(getId(), getEmail(), null, getDate(), null, null);

        if (!mPresenter.validateDataInput(registerRequest)) {
            return;
        }
        if (!emailValidator(mEmail)) {
            showToast(R.string.email_error);
            return;
        }
        if (mSelectedTypePosition == DEFAULT_VALUE) {
            showToast(R.string.please_select_type);
            return;
        }
        mContext.startActivity(
                new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        notifyPropertyChanged(BR.viewModel);
    }

    public void onClickSelectDate() {
        mDialogManager.showDatePickerDialog();
    }

    private void showToast(int stringId) {
        Toast toast = Toast.makeText(mContext, stringId, Toast.LENGTH_SHORT);
        View view = toast.getView();
        toast.setGravity(Gravity.BOTTOM | Gravity.HORIZONTAL_GRAVITY_MASK, 0, 0);
        view.setBackgroundResource(android.R.color.black);
        toast.setView(view);
        toast.show();
    }
}
