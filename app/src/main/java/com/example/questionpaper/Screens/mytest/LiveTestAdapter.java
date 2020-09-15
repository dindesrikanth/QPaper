package com.example.questionpaper.Screens.mytest;

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
import com.example.questionpaper.Response.mytests.LiveTest.TestData;
import com.example.questionpaper.Response.mytests.LiveTest.Tests;

import java.util.List;

public class LiveTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    RootViewClickInterface rootViewClickInterface;
    List<Object> listData;
    List<TestData> responseActualData;

    public LiveTestAdapter(RootViewClickInterface rootViewClickInterface, List<Object> listData,
                           List<TestData> responseActualData) {
        this.rootViewClickInterface = rootViewClickInterface;
        this.listData = listData;
        this.responseActualData = responseActualData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.common_expandable_header_layout,parent,false);
                return new HeaderViewHolder(v1);
            case 1:
                View listItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.live_tests_adapter, parent, false);
                ViewHolder viewHolder = new ViewHolder(listItem);
                return viewHolder;
            case -1:
                return null;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                TestData testData = (TestData) listData.get(position);
                headerViewHolder.tvHeaderTitle.setText(testData.getCourseName());
                if (!testData.getDownArrow()) {
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    headerViewHolder.imgArrow.setImageResource(R.drawable.ic_arrow_drop_up);
                }
                break;
            case 1:
                ViewHolder vh=(ViewHolder)holder;
                Tests tests=(Tests)listData.get(position);

                if (!TextUtils.isEmpty(tests.getName())) {
                    vh.tvTestName.setText(tests.getName());
                } else {
                    vh.tvTestName.setText("-");
                }

                if (!TextUtils.isEmpty(tests.getCourseName())) {
                    vh.tvCourseName.setText(tests.getCourseName());
                } else {
                    vh.tvCourseName.setText("-");
                }

                vh.tvTakeTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  Toast.makeText(vh.tvTakeTest.getContext(), "Test is Ready To Start", Toast.LENGTH_SHORT).show();
                    }
                });

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTestName,tvCourseName;
        private TextView tvTakeTest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestName =(TextView)itemView.findViewById(R.id.tvTestName);
            tvCourseName  = (TextView)itemView.findViewById(R.id.tvCourseName);
            tvTakeTest = (TextView)itemView.findViewById(R.id.tvTakeTest);
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
