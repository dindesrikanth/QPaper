package com.example.questionpaper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Course;
import com.example.questionpaper.R;

import java.util.ArrayList;
import java.util.List;

public class Loadcources_Recyclerviewadapter extends RecyclerView.Adapter<Loadcources_Recyclerviewadapter.CategoryViewHolder> {
    private List<Course> courseList;
    Context context;
   ArrayList<Long> user_courses = new ArrayList<Long>();
    private Loadcources_Recyclerviewadapter.OnItemClickListenerload mListener;
    public interface OnItemClickListenerload {
        void quesclick(ArrayList<Long> checkid);
    }
    public void setOnItemClickListener(Loadcources_Recyclerviewadapter.OnItemClickListenerload listener) {
        mListener = listener;
    }

    public Loadcources_Recyclerviewadapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @Override
    public Loadcources_Recyclerviewadapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadcources_view, parent, false);
        Loadcources_Recyclerviewadapter.CategoryViewHolder categoryViewHolder = new Loadcources_Recyclerviewadapter.CategoryViewHolder(categoryProductView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {
holder.courses.setText(courseList.get(position).getCourse_name());
holder.courses.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.courses.setBackgroundColor(context.getResources().getColor(R.color.green));

        user_courses.add(courseList.get(position).getId());
       // Log.i("msg",""+user_courses);
        if (mListener != null) {
            int position1 = position;
            if (position1 != RecyclerView.NO_POSITION) {
                mListener.quesclick(user_courses);
                Log.i("msg",""+user_courses);
               // mListener.Responcemap(Responce);
              //  Log.i("mapattemptsc",""+mapattempt);
            }
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView courses;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            courses = itemView.findViewById(R.id.tvQuestionNumber);
        }
    }
}
