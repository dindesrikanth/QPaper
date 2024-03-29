package com.example.questionpaper.Common;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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
    private String errorTextViewValue;
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
    public void setValueToLayoutNew(String editTextHint, String textViewValue,String errorText){
        this.editTextHint= editTextHint;
        this.textViewValue = textViewValue;
        this.errorTextViewValue= errorText;
        setDataOnUiNew();
    }

   public void makeEnableOrDisable(boolean isEnabled){
        if(isEnabled){
            customEditText.setEnabled(true);
        }else{
            customEditText.setEnabled(false);
        }
    }

    public void setEnable(boolean isEnable){
        if(isEnable){
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
    private void setDataOnUiNew(){
        customEditText.setText(textViewValue);
        editTextLabel.setText(editTextHint);
        editTextLabel.setTextColor(Color.parseColor("#000000"));
        editTextErrorLabel.setVisibility(View.VISIBLE);
        editTextErrorLabel.setText(errorTextViewValue);
        editTextErrorLabel.setTextColor(Color.parseColor("#FF0000"));
    }

    public String getEditTextValue(){
        return customEditText.getText().toString();
    }

    public void setMaxLength(int maxLength){
        customEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength),new EmojiExcludeFilter()});
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

    public void setInputTypeAndLength(String inputType,int length){
        if("number".equalsIgnoreCase(inputType)){
            customEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            customEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(length)});
        }else if("password".equalsIgnoreCase(inputType)){
            customEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            customEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }if("alphaNumericSpecialChar".equalsIgnoreCase(inputType)){
            customEditText.setFilters(new InputFilter[]{
                    new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence src, int start, int end, Spanned spanned, int dStart, int dEnd) {
                            if (src.equals("")){
                                return src;
                            }if(src.toString().matches("[a-zA-Z 0-9#@$&()-+=_<>?/;:.]")){
                                return src;
                            }
                            return "";
                        }
                    }, new InputFilter.LengthFilter(length)
            });
        }
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


    public void setEditTextEnable(boolean isEnabled){
        if(isEnabled){
            customEditText.setEnabled(true);
            customEditText.setCursorVisible(true);
        }else{
            customEditText.setEnabled(false);
            customEditText.setCursorVisible(false);
        }
    }

}
