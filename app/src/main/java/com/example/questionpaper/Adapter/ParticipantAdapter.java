package com.example.questionpaper.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.AppCourseModel;
import com.example.questionpaper.Model.ParticipantModel;
import com.example.questionpaper.R;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {
    private List<String> item;
    private Context context ;
//    private ParticipantAdapter.OnItemClickListener mListener;

    public ParticipantAdapter(List<String> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ParticipantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        ParticipantAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.participant_item,  parent, false);
        vh = new ParticipantAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ParticipantAdapter.ViewHolder holder, final int position) {
        holder.participant_name.setText(item.get(position));
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public void setItem(List<ParticipantModel> item){

    }

//    public interface OnItemClickListener {
//        void onCourseClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
                TextView participant_name;
                RelativeLayout parent_view;
                public ViewHolder(View itemView) {
                    super(itemView);
                    participant_name = itemView.findViewById(R.id.participant_name);
                    parent_view = itemView.findViewById(R.id.parent_view);
                }

            }


    }