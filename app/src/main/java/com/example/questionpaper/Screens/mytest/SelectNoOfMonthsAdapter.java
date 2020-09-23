package com.example.questionpaper.Screens.mytest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;

import java.util.List;

public class SelectNoOfMonthsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnAdapterItemClicked adapterItemClicked;
    List<NoOfMonthsModel> listData;

    public SelectNoOfMonthsAdapter(OnAdapterItemClicked adapterItemClicked, List<NoOfMonthsModel> listData) {
        this.adapterItemClicked = adapterItemClicked;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_textview_layout,parent,false);
        return new HeaderViewHolder(v1);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HeaderViewHolder holder1=(HeaderViewHolder)holder;
        holder1.rdButton.setText(listData.get(position).getTitle());
        holder1.rdButton.setChecked(listData.get(position).getIsSelected());
        if(listData.get(position).getIsSelected()){
            Utility.my_test_months_data= Integer.parseInt(listData.get(position).getTitle());
        }



    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RadioButton rdButton;
        private LinearLayout lnrRadioLayout;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            lnrRadioLayout=itemView.findViewById(R.id.lnrRadioLayout);
            rdButton = itemView.findViewById(R.id.rdButton);
            rdButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterItemClicked.onItemSelected(getAdapterPosition());
        }
    }

    interface OnAdapterItemClicked{
        void onItemSelected(int position);
    }

}
