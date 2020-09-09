package com.example.questionpaper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.PrizeModel;
import com.example.questionpaper.R;

import java.util.List;

public class PrizeAdapter extends RecyclerView.Adapter<PrizeAdapter.ViewHolder> {
    private List<PrizeModel> item;
    private Context context ;
//    private PrizeAdapter.OnItemClickListener mListener;

    public PrizeAdapter(List<PrizeModel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public PrizeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        PrizeAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.prize_item,  parent, false);
        vh = new PrizeAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PrizeAdapter.ViewHolder holder, final int position) {
        holder.prize_title.setText(item.get(position).getRange());
        holder.prize_value.setText(item.get(position).getPercentage());
//        holder.parent_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mListener != null){
//                    mListener.onClick(position);
//                }
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

//    public interface OnItemClickListener {
//        void onCourseClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
                TextView prize_title, prize_value;
                RelativeLayout parent_view;

                public ViewHolder(View itemView) {
                    super(itemView);
                    prize_title = itemView.findViewById(R.id.prize_title);
                    prize_value = itemView.findViewById(R.id.prize_value);
                    parent_view = itemView.findViewById(R.id.parent_view);
                }

            }


    }