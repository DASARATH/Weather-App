package com.example.gopu.myweather.widgets;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by dasaratharamireddygopu on 11/11/17.
 */

class ClearableEditTextBehaviour {

    public static void setupListeners(final EditText clearableEditText, final View clearTextButton) {
        clearTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearableEditText.setText("");
            }
        });

        clearableEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateClearButtonVisibility(clearableEditText, clearTextButton);
            }
        });
    }

    private static void updateClearButtonVisibility(EditText clearableEditText, View clearTextButton) {
        clearTextButton.setVisibility(clearableEditText.getText().length() >0 ? View.VISIBLE : View.GONE);
    }
}
