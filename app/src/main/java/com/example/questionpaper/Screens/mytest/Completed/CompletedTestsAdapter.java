package com.example.questionpaper.Screens.mytest.Completed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTest.TestData;
import com.example.questionpaper.Response.mytests.LiveTest.Tests;
import com.example.questionpaper.Screens.mytest.RootViewClickInterface;

import java.util.List;

public class CompletedTestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    RootViewClickInterface rootViewClickInterface;
    List<Object> listData;
    List<TestData> responseActualData;
    AdapterInterface adapterInterface;

    public CompletedTestsAdapter(RootViewClickInterface rootViewClickInterface, List<Object> listData,
                                 List<TestData> responseActualData,AdapterInterface adapterInterface){
        this.rootViewClickInterface = rootViewClickInterface;
        this.listData = listData;
        this.responseActualData = responseActualData;
        this.adapterInterface=adapterInterface;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       switch (viewType){
           case 0:
               View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.common_expandable_header_layout,parent,false);
               return new HeaderViewHolder(v1);
           case 1:
               View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytests_completed_test_adapter,parent,false);
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
                TestData testData=(TestData)listData.get(position);
                headerViewHolder.tvHeaderTitle.setText(testData.getCourseName());
                if(!testData.getDownArrow()){
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_down);
                }else{
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_up);
                }
                break;
            case 1:
                AdapterViewHolder vh=(AdapterViewHolder)holder;
                Tests tests=(Tests)listData.get(position);
                vh.tvTestName.setText(tests.getName());
                vh.tvDate.setText(tests.getDate());
                vh.tvTime.setText(tests.getTestTime());
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

    public interface AdapterInterface{
        void detailedAnalysisButtonClicked(int position);
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTestName,tvDate,tvTime,tvDetailedAnalysis;
        public AdapterViewHolder(View v) {
            super(v);
            tvTestName=v.findViewById(R.id.tvTestName);
            tvDate=v.findViewById(R.id.tvDate);
            tvTime=v.findViewById(R.id.tvTime);
            tvDetailedAnalysis=v.findViewById(R.id.tvDetailedAnalysis);
            tvDetailedAnalysis.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterInterface.detailedAnalysisButtonClicked(getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(listData.get(position) instanceof TestData){
            return 0;
        }else if(listData.get(position) instanceof Tests){
            return 1;
        }
        return -1;
    }
}


