package com.example.questionpaper.Screens.Courses;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.Courses.CoursesDetails;

import java.util.List;

public class CoursesListScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CoursesDetails> coursesDetailsList;
    private CoursesListInterface coursesListInterface;
    public CoursesListScreenAdapter(CoursesListInterface coursesListInterface,List<CoursesDetails> coursesDetailsList){
        this.coursesDetailsList = coursesDetailsList;
        this.coursesListInterface = coursesListInterface;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_list_adapter,parent,false);
        return new AdapterViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterViewHolder vh=(AdapterViewHolder)holder;
        vh.tvCourseName.setText(coursesDetailsList.get(position).getLabel());
        if(coursesDetailsList.get(position).isSelected()){
            vh.tvCourseName.setBackgroundResource(R.drawable.box_border_green_bg);
        }else{
            vh.tvCourseName.setBackgroundResource(0);
        }
    }

    @Override
    public int getItemCount() {
        return coursesDetailsList.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCourseName;
        public AdapterViewHolder(View v) {
            super(v);
            tvCourseName=v.findViewById(R.id.tvCourseName);
            tvCourseName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            coursesListInterface.onCourseSelected(getAdapterPosition(),coursesDetailsList);
        }
    }

    interface CoursesListInterface{
        void onCourseSelected(int position,List<CoursesDetails> coursesDetailsList);
    }

}

