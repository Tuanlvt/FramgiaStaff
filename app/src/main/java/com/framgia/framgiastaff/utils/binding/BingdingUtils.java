package com.framgia.framgiastaff.utils.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;

/**
 * Created by levutantuan on 7/2/17.
 */

public class BingdingUtils {

    @BindingAdapter("errorText")
    public static void setErrorText(EditText editText, String text) {
        editText.setError(text);
    }

    @BindingAdapter({ "spinnerAdapter" })
    public static void setAdapterForSpinner(AppCompatSpinner spinner,
            ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("errorTextInputLayout")
    public static void setErrorTextInputLayout(final TextInputLayout textInputLayout,
            final String text) {
        textInputLayout.setError(text);
        EditText editText = textInputLayout.getEditText();
        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No-Op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    textInputLayout.setError(text);
                }
                if (s.length() > 0) {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //No-Op
            }
        });
    }
}
