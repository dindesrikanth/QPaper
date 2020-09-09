package com.example.questionpaper.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Question_view_adapter extends RecyclerView.Adapter<Question_view_adapter.ViewHolder> {
    private List<Questionesmodel> item;
    private Context context ;
    private static int currentPosition = 1;
    final  String KEY_SAVED_INDEX = "SAVE_iNDEX";
    final  String KEY_SAVED_Question = "SAVE_NUMBER";

    public Question_view_adapter(List<Questionesmodel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public Question_view_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        Question_view_adapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.question_view_row,  parent, false);
        vh = new Question_view_adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Question_view_adapter.ViewHolder holder, final int position) {
        Log.i("autolog", "onBindViewHolder");
        int srno = position+1;
        final int row_index = -1;
        // position = currentPosition;
        Questionesmodel hero = item.get(position);
        // holder.responcetxt.setText(item.get(position).setResponce(););
        holder.txt_quetion.setText(srno+hero.getQues_detail());
        holder.radio1.setText(hero.getOpta());
        holder.radio2.setText(hero.getOptb());
        holder.radio3.setText(hero.getOptc());
        holder.radio4.setText(hero.getOptd());
        if (currentPosition == position) {
            //creating an animation
           // Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.lay.setVisibility(View.VISIBLE);

            //adding sliding effect
           // holder.linearLayout.startAnimation(slideDown);
        }
        holder.btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });

        holder.radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {

                final RadioButton checkedRadioButton = (RadioButton) holder.radiogrp.findViewById(checkedId);
                int checkedindex = holder.radiogrp.indexOfChild(checkedRadioButton);
                holder.responcetxt.setText("checkedID" + checkedId);
                Savepreferences(KEY_SAVED_INDEX, checkedindex);

            }
        });
    }
    @Override
    public int getItemCount() {
        Log.i("autolog", "getItemCount");
        Integer j = item.size();
        return j != null ? j : 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
                //public TextView ques, hobby;


                TextView txt_quetion,responcetxt,responcetxt2;
                public RadioGroup radiogrp;
                RadioButton radio1,radio2,radio3,radio4,radiobutton;
              LinearLayout lay;
                Button btn_Clear;

                public ViewHolder(View itemView) {
                    super(itemView);
                    Log.i("autolog", "ViewHolder");
                    responcetxt = (TextView) itemView.findViewById(R.id.responcetxt) ;
                    responcetxt2 = (TextView) itemView.findViewById(R.id.responcetxt2) ;
                    txt_quetion = (TextView) itemView.findViewById(R.id.quest);
                    radiogrp = (RadioGroup) itemView.findViewById(R.id.radio_group);
                    radio1 = (RadioButton) itemView.findViewById(R.id.A);
                    radio2 = (RadioButton) itemView.findViewById(R.id.B);
                    radio3 = (RadioButton) itemView.findViewById(R.id.C);
                    radio4 = (RadioButton) itemView.findViewById(R.id.D);
                    lay =(LinearLayout)itemView.findViewById(R.id.linearLayout);
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                    btn_Clear = (Button)itemView.findViewById(R.id.clear);

                }

            }

        private  void Savepreferences(String key,int value){
            SharedPreferences sharedPreferences = context.getSharedPreferences("MY_PREF",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key,value);
            editor.commit();
        }
        private void LoadPrefrences(){
            SharedPreferences sharedPreferences =context.getSharedPreferences("MY_PREF",MODE_PRIVATE);
            int quno = sharedPreferences.getInt(KEY_SAVED_Question,0);
            Log.i("saveinRA","ID"+quno);
            //RadioButton savedcheckedRadiobutton =(RadioButton)RG.getChildAt(savedradioindex);
            // savedcheckedRadiobutton.setChecked(true);
            //optionsget.setText("ID"+savedradioindex);
        }
    }