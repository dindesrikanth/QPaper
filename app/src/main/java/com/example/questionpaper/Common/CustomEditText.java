package com.example.questionpaper.Common;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.questionpaper.R;

import java.util.concurrent.atomic.AtomicInteger;

public class CustomEditText extends LinearLayout {
    private TextView editTextLabel;
    private EditText customEditText;
    private TextView editTextErrorLabel;

    private String editTextHint;
    private String textViewValue;
    private Context context;

    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        final LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_edittext_layout,this);
        initialiseComponents();
        removeEmoji();
    }

    private void removeEmoji(){
        customEditText.setFilters(new InputFilter[]{new EmojiExcludeFilter()});
    }

    private void initialiseComponents(){
        customEditText=findViewById(R.id.customEditText);
        editTextLabel=findViewById(R.id.editTextLabel);
        editTextErrorLabel=findViewById(R.id.editTextErrorLabel);
        customEditText.setId(generateViewId());

        customEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //doSomething();
                editTextErrorLabel.setText("");
                editTextErrorLabel.setVisibility(View.GONE);
            }
        });
    }
    public void setValueToLayout(String editTextHint, String textViewValue){
        this.editTextHint= editTextHint;
        this.textViewValue = textViewValue;
        setDataOnUi();
    }
   public void makeEnableOrDisable(boolean isEnabled){
        if(isEnabled){
            customEditText.setEnabled(true);
        }else{
            customEditText.setEnabled(false);
        }
    }

    private void setDataOnUi(){
        customEditText.setText(textViewValue);
        editTextLabel.setText(editTextHint);
        editTextLabel.setTextColor(Color.parseColor("#000000"));
        editTextErrorLabel.setVisibility(View.GONE);
    }

    public String getEditTextValue(){
        return customEditText.getText().toString();
    }

    public void setMaxValue(int maxValue){
        customEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxValue),new EmojiExcludeFilter()});
    }

    public void setEditTextValue(String editTextString){
        editTextLabel.setText(editTextString);
        editTextLabel.setTextColor(Color.parseColor("#000000"));
    }

    private void clearFocusOfEditText() {
        customEditText.clearFocus();
        customEditText.setBackgroundResource(R.drawable.edittext_border);
    }

    private void setImeOption(int actionDone){
        if(Utility.ACTION_DONE == actionDone){
            customEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void setBackgroundEditText(){
        customEditText.clearFocus();
        customEditText.setBackground(context.getResources().getDrawable(R.drawable.edittext_border));
    }

    public void setEditTextErrorLabel(String errorMessage){
        customEditText.setText("");
        editTextErrorLabel.setVisibility(View.VISIBLE);
        editTextErrorLabel.setText(errorMessage);
    }


    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    public static int generateViewId(){
        for (; ;){
            final int result = sNextGeneratedId.get();
            int newValue = result +1;
            if(newValue > 0x00FFFFFF) newValue = 1;
            if(sNextGeneratedId.compareAndSet(result,newValue)){
                return result;
            }
        }
    }

}
