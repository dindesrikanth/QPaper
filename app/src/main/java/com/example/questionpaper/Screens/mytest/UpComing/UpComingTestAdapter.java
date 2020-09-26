package com.example.questionpaper.Screens.mytest.UpComing;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.UpComing.Data;
import com.example.questionpaper.Response.mytests.UpComing.Tests;

import java.util.List;

public class UpComingTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RootViewClickInterface rootViewClickInterface;
    List<Object> listData;
    List<Data> responseActualData;

    public UpComingTestAdapter(RootViewClickInterface rootViewClickInterface, List<Object> listData, List<Data> data) {

        this.rootViewClickInterface = rootViewClickInterface;
        this.listData = listData;
        this.responseActualData = data;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.common_expandable_header_layout,parent,false);
                return new HeaderViewHolder(v1);
            case 1:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytests_upcoming_adapter,parent,false);
                return new AdapterViewHolder(v2);

            case -1:
                return null;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case 0:
                HeaderViewHolder headerViewHolder=(HeaderViewHolder)holder;
                Data data=(Data)listData.get(position);
                headerViewHolder.tvHeaderTitle.setText(data.getCourseName());
                if(!data.getDownArrow()){
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_down);
                }else{
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_up);
                }
                break;
            case 1:
                AdapterViewHolder vh=(AdapterViewHolder)holder;
                Tests tests =(Tests)listData.get(position);
                vh.tvTestName.setText(tests.getName());
                vh.tvDate.setText(tests.getDate());
                vh.tvTime.setText(tests.getTestTime());
                String marksAndDuration="";
                if(!TextUtils.isEmpty(tests.getTotalMarks())){
                    marksAndDuration = marksAndDuration+tests.getTotalMarks()+" Marks ";
                }
                if(!TextUtils.isEmpty(tests.getDuration())){
                    marksAndDuration = marksAndDuration+tests.getDuration()+" Minutes";
                }
                vh.tvMarksAndDuration.setText(marksAndDuration);
                break;
            case -1:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    private class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout lnrRootLayout;
        TextView tvHeaderTitle;
        ImageView imgArrow;
        public HeaderViewHolder(View v) {
            super(v);
            lnrRootLayout=v.findViewById(R.id.lnrRootLayout);
            tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
            imgArrow=v.findViewById(R.id.imgArrow);

            lnrRootLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rootViewClickInterface.onRootViewClicked(getAdapterPosition(),responseActualData);
        }
    }

    public interface RootViewClickInterface{
        void onRootViewClicked(int position, List<Data> responseActualData);
    }


    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestName,tvDate,tvTime,tvMarksAndDuration;
        public AdapterViewHolder(View v) {
            super(v);
            tvTestName=v.findViewById(R.id.tvTestName);
            tvDate=v.findViewById(R.id.tvDate);
            tvTime=v.findViewById(R.id.tvTime);
            tvMarksAndDuration=v.findViewById(R.id.tvMarksAndDuration);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(listData.get(position) instanceof Data){
            return 0;
        }else if(listData.get(position) instanceof Tests){
            return 1;
        }
        return -1;
    }
}
