package com.example.ghtk.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MyTextWatcher implements TextWatcher {
    private EditText mEditText;
    public MyTextWatcher(EditText editText){
        this.mEditText = editText;
    }

    public EditText getmEditText() {
        return mEditText;
    }

    public void setmEditText(EditText mEditText) {
        this.mEditText = mEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
