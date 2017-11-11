package com.example.gopu.myweather.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.gopu.myweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dasaratharamireddygopu on 11/11/17.
 */

public class ClearableEditText extends FrameLayout {

    @BindView(R.id.clearable_edittext)
    EditText mClearableEditText;

    @BindView(R.id.clearable_edittext_icon)
    View mClearTextButton;

    public ClearableEditText(@NonNull Context context) {
        this(context, null);
        init(null);
    }

    public ClearableEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(attrs);
    }

    public ClearableEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.clearable_edit_text_view, this);
        ButterKnife.bind(this);

        ClearableEditTextBehaviour.setupListeners(mClearableEditText, mClearTextButton);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if(attributeSet != null) {
            TypedArray styleAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ClearableEditTextWidget, 0, 0);
            try {
                String hintText = styleAttributes.getString(R.styleable.ClearableEditTextWidget_android_hint);
                mClearableEditText.setHint(hintText);
                int imeOptions = styleAttributes.getInt(R.styleable.ClearableEditTextWidget_android_imeOptions, EditorInfo.IME_NULL);
                mClearableEditText.setImeOptions(imeOptions);
            } finally {
                styleAttributes.recycle();
            }
        }
    }

    public EditText getClearableEditText() {
        return mClearableEditText;
    }

    public void setEditable(boolean editable) {
        mClearableEditText.setFocusable(editable);
        mClearableEditText.setFocusableInTouchMode(editable);
    }
}
