package com.framgia.framgiastaff.widgets.dialog;

import android.app.DatePickerDialog;

/**
 * Created by levutantuan on 7/2/17.
 */

public interface DialogManagerInterface {

    void showDatePickerDialog();

    DialogManagerInterface dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);
}
