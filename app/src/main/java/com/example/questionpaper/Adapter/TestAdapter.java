package com.example.questionpaper.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<Questionesmodel> item;
    private Context context ;
    private TestAdapter.OnItemClickListener mListener;

    public TestAdapter(List<Questionesmodel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        TestAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.test_item,  parent, false);
        vh = new TestAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final TestAdapter.ViewHolder holder, final int position) {
        Questionesmodel hero = item.get(position);
        holder.test_question_text.setText(hero.getQues_detail());
        holder.option1.setVisibility(View.VISIBLE);
        holder.option2.setVisibility(View.VISIBLE);
        holder.option3.setVisibility(View.VISIBLE);
        holder.option4.setVisibility(View.VISIBLE);
        holder.option5.setVisibility(View.VISIBLE);
        if(!TextUtils.isEmpty(hero.getOpta())) {
            holder.test_option_text_1.setText(hero.getOpta());
        }else{
            holder.option1.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(hero.getOptb())) {
            holder.test_option_text_2.setText(hero.getOptb());
        }else{
            holder.option2.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(hero.getOptc())) {
            holder.test_option_text_3.setText(hero.getOptc());
        }else{
            holder.option3.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(hero.getOptd())) {
            holder.test_option_text_4.setText(hero.getOptd());
        }else{
            holder.option4.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(hero.getOpte())) {
            holder.test_option_text_5.setText(hero.getOpte());
        }else{
            holder.option5.setVisibility(View.GONE);
        }

        holder.option1.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
        holder.option2.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
        holder.option3.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
        holder.option4.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
        holder.option5.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));

        if(!TextUtils.isEmpty(hero.getAnswerId())){
            switch (hero.getAnswerId()){
                case "1":
                    holder.option1.setBackground(context.getResources().getDrawable(R.drawable.box_border_blue_grey_bg));
                    break;
                case "2":
                    holder.option2.setBackground(context.getResources().getDrawable(R.drawable.box_border_blue_grey_bg));
                    break;
                case "3":
                    holder.option3.setBackground(context.getResources().getDrawable(R.drawable.box_border_blue_grey_bg));
                    break;
                case "4":
                    holder.option4.setBackground(context.getResources().getDrawable(R.drawable.box_border_blue_grey_bg));
                    break;
                case "5":
                    holder.option5.setBackground(context.getResources().getDrawable(R.drawable.box_border_blue_grey_bg));
                    break;
            }
        }

        holder.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(1, position);
            }
        });

        holder.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(2, position);
            }
        });

        holder.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(3, position);
            }
        });

        holder.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(4, position);
            }
        });

        holder.option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(5, position);
            }
        });

        if(hero.isMarked()){
            holder.star_view.setImageResource(R.drawable.ic_star_selected);
        }else{
            holder.star_view.setImageResource(R.drawable.ic_star_unselected);
        }

        holder.test_question_count.setText(position + 1 +"");
        mListener.onPageSeen(position);
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public interface OnItemClickListener {
        void optionsclick(int checkid, int position);
        void onPageSeen(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
                //public TextView ques, hobby;


                TextView test_question_text, test_question_count;
                TextView test_option_text_1, test_option_text_2, test_option_text_3, test_option_text_4, test_option_text_5;
                LinearLayout option1,option2,option3,option4,option5;
                ImageView star_view;
                public ViewHolder(View itemView) {
                    super(itemView);
                    test_question_text = (TextView) itemView.findViewById(R.id.test_question_text) ;
                    test_question_count = (TextView) itemView.findViewById(R.id.test_question_count) ;
                    test_option_text_1 = (TextView) itemView.findViewById(R.id.test_option_text_1) ;
                    test_option_text_2 = (TextView) itemView.findViewById(R.id.test_option_text_2) ;
                    test_option_text_3 = (TextView) itemView.findViewById(R.id.test_option_text_3) ;
                    test_option_text_4 = (TextView) itemView.findViewById(R.id.test_option_text_4) ;
                    test_option_text_5 = (TextView) itemView.findViewById(R.id.test_option_text_5) ;
                    option1 = (LinearLayout) itemView.findViewById(R.id.option1) ;
                    option2 = (LinearLayout) itemView.findViewById(R.id.option2) ;
                    option3 = (LinearLayout) itemView.findViewById(R.id.option3) ;
                    option4 = (LinearLayout) itemView.findViewById(R.id.option4) ;
                    option5 = (LinearLayout) itemView.findViewById(R.id.option5) ;
                    star_view = (ImageView) itemView.findViewById(R.id.star_view) ;
                }

            }


    }