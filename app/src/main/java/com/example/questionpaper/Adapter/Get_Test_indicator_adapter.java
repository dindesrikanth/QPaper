package com.example.questionpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.R;

import java.util.List;

public class Get_Test_indicator_adapter extends RecyclerView.Adapter<Get_Test_indicator_adapter.CategoryViewHolder> {
    private List<Get_user_test> testlist;
    private List<String> courselist;
    Context context;
    String course_first;
    private  int selectedIndex;

    public Get_Test_indicator_adapter(List<Get_user_test> testlist, Context context, String course_first) {
        this.testlist = testlist;
        this.context = context;
        this.course_first = course_first;
        selectedIndex = 0;
    }

    @NonNull
    @Override
    public Get_Test_indicator_adapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_usertest_view, parent, false);
        Get_Test_indicator_adapter.CategoryViewHolder categoryViewHolder = new Get_Test_indicator_adapter.CategoryViewHolder(categoryProductView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Get_Test_indicator_adapter.CategoryViewHolder holder, int position) {
        Get_user_test Test  = testlist.get(position);
    holder.totalmarks_text.append(testlist.get(position).getTot_marks()+"."+testlist.get(position).getTest_time()+"."+testlist.get(position).getTot_ques());
    holder.title.append(testlist.get(position).getCourse_name()+"-"+testlist.get(position).getSub_name());
  holder.prize.setText(""+testlist.get(position).getPrz_amt_total());
    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView totalmarks_text,title,prize,time;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            totalmarks_text = itemView.findViewById(R.id.totalmarks);
            title = itemView.findViewById(R.id.Test_title);
            prize = itemView.findViewById(R.id.totalprize);
            time= itemView.findViewById(R.id.testtime);
        }
    }
}
