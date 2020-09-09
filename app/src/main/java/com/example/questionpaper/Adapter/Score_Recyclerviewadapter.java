package com.example.questionpaper.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Fragments.RecylerViewAdapter;
import com.example.questionpaper.Model.ScoreModel;
import com.example.questionpaper.R;

import java.util.List;

public class Score_Recyclerviewadapter extends RecyclerView.Adapter<Score_Recyclerviewadapter.ViewHolder>{
ScoreModel scoremodelitem ;
Context context;

    public Score_Recyclerviewadapter(ScoreModel scoremodelitem, Context context) {
        this.scoremodelitem = scoremodelitem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Score_Recyclerviewadapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.score_list_view, parent, false);
        vh = new Score_Recyclerviewadapter.ViewHolder(view);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.text_id.setText(Integer.toString((int) scoremodelitem.getTest_id()));
        holder.use_id.setText(Integer.toString((int) scoremodelitem.getUser_id()));
        holder.time_spent.setText(Integer.toString((int) scoremodelitem.getTime_spent()));
        holder.achived_marks.setText(Integer.toString((int) scoremodelitem.getAchieved_marks()));
        holder.correct.setText(Integer.toString((int) scoremodelitem.getQues_right()));
        holder.incorrect.setText(Integer.toString((int) scoremodelitem.getQues_wrong()));
        holder.unattempted.setText(Integer.toString((int) scoremodelitem.getQues_unattempted()));
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_id,use_id,time_spent,achived_marks,correct,incorrect,unattempted;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_id = itemView.findViewById(R.id.text_id_text);
            use_id = itemView.findViewById(R.id.user_id_text);
            time_spent = itemView.findViewById(R.id.time_spent_text);
            achived_marks = itemView.findViewById(R.id.achieved_marks_text);
            correct = itemView.findViewById(R.id.correct_text);
            incorrect = itemView.findViewById(R.id.incorrect_text);
            unattempted = itemView.findViewById(R.id.unattemted_text);
        }
    }
}
