package com.example.questionpaper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.DashboardModelNew;
import com.example.questionpaper.R;

import java.util.List;

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {
    private List<DashboardModelNew.DashBoardTests> item;
    private Context context ;
    private CourseItemAdapter.OnItemClickListener mListener;

    public CourseItemAdapter(List<DashboardModelNew.DashBoardTests> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public CourseItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        CourseItemAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.course_valuelist_item_new,  parent, false);
        vh = new CourseItemAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseItemAdapter.ViewHolder holder, final int position) {
//      holder.test_title.setText("SSC JHT 2020 : Full Mock Test" + " " + (position + 1));
//        holder.test_fees.setText("" + (10 + (position * 2)));
//        holder.time_left.setText("Ends in " + (position + 1) +" Days");
//        holder.parent_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mListener != null){
//                    mListener.onCourseItemClick(position);
//                }
//            }
//        });

        holder.test_title.setText(item.get(position).getTestName());
        holder.test_fees.setText(item.get(position).getTestFee());
        holder.total_prize_text_value.setText(item.get(position).getTotalPrize() + "");
        holder.question_count.setText(item.get(position).getTotalQuestions() +"");
        holder.minutes.setText(item.get(position).getTestDuration() + "");
        holder.time_value.setText(item.get(position).getTestTime());
        holder.price_amount.setText(item.get(position).getFirstPrize()+ "");
        holder.date_value.setText(item.get(position).getTestDate());
        holder.slots_count.setText(item.get(position).getTotalParticipants() + "");
        //holder.time_left.setText(item.get(position).getTimeLeft());
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onCourseItemClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public interface OnItemClickListener {
        void onCourseItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setItems(List<DashboardModelNew.DashBoardTests> item){
        this.item = item;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
                TextView test_title, test_fees, total_prize_text_value, question_count, minutes, price_amount, time_value, date_value, slots_count, time_left;
                LinearLayout parent_view;
                public ViewHolder(View itemView) {
                    super(itemView);
                    test_title = itemView.findViewById(R.id.test_title);
                    test_fees = itemView.findViewById(R.id.test_fees);
                    total_prize_text_value = itemView.findViewById(R.id.total_prize_text_value);
                    question_count = itemView.findViewById(R.id.question_count);
                    minutes = itemView.findViewById(R.id.minutes);
                    time_value = itemView.findViewById(R.id.time_value);
                    price_amount = itemView.findViewById(R.id.price_amount);
                    date_value = itemView.findViewById(R.id.date_value);
                    slots_count = itemView.findViewById(R.id.slots_count);
                    parent_view = itemView.findViewById(R.id.parent_view);
                    time_left = itemView.findViewById(R.id.time_left);
                }

            }


    }