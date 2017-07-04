package com.framgia.framgiastaff.widgets.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import java.util.Calendar;

/**
 * Created by levutantuan on 7/2/17.
 */

public class DialogManager implements DialogManagerInterface {

    private Context mContext;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;

    public DialogManager(Context context) {
        mContext = context;
        mCalendar = Calendar.getInstance();
    }

    @Override
    public DialogManagerInterface dialogDatePicker(
            DatePickerDialog.OnDateSetListener onDateSetListener) {
        mDatePickerDialog =
                new DatePickerDialog(mContext, onDateSetListener, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        return this;
    }

    @Override
    public void showDatePickerDialog() {
        if (mDatePickerDialog == null) {
            return;
        }
        mDatePickerDialog.show();
    }
}
