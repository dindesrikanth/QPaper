package com.example.questionpaper.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.AppCourseModel;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<AppCourseModel> item;
    private Context context ;
    private CourseAdapter.OnItemClickListener mListener;

    public CourseAdapter(List<AppCourseModel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        CourseAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.course_item,  parent, false);
        vh = new CourseAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseAdapter.ViewHolder holder, final int position) {
        holder.course_title.setText(item.get(position).getCourse_name());
        if(item.get(position).isSelected()){
            holder.course_title.setTextColor(context.getColor(R.color.test_button_blue));
            holder.selected_highlight_view.setBackgroundColor(context.getColor(R.color.test_button_blue));
        }else{
            holder.course_title.setTextColor(Color.BLACK);
            holder.selected_highlight_view.setBackgroundColor(Color.WHITE);
        }
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onCourseClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public interface OnItemClickListener {
        void onCourseClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
                TextView course_title;
                View selected_highlight_view;
                RelativeLayout parent_view;
                public ViewHolder(View itemView) {
                    super(itemView);
                    course_title = itemView.findViewById(R.id.course_title);
                    selected_highlight_view = itemView.findViewById(R.id.selected_highlight_view);
                    parent_view = itemView.findViewById(R.id.parent_view);
                }

            }


    }