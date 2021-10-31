package com.example.questionpaper.Screens.Payments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.Payments.Transactions;

import java.util.List;

public class TransactionHistoryAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> listData;

    public TransactionHistoryAdapter(List<Object> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.header_with_left_text,parent,false);
                return new HeaderViewHolder(v1);
            case 1:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_history_adapter,parent,false);
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
                String date=(String)listData.get(position);
                headerViewHolder.tvHeader.setText(date);

                break;
            case 1:
                AdapterViewHolder vh=(AdapterViewHolder)holder;
                Transactions transactions =(Transactions)listData.get(position);
                vh.tvOrderId.setText(transactions.getTransactionId());
                vh.tvType.setText(transactions.getTransactionType()+" of "+transactions.getAmmount());
                break;
            case -1:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView tvHeader;
        public HeaderViewHolder(View v) {
            super(v);
            tvHeader=v.findViewById(R.id.tvHeader);
        }

    }


    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId,tvType;
        public AdapterViewHolder(View v) {
            super(v);
            tvOrderId=v.findViewById(R.id.tvOrderId);
            tvType=v.findViewById(R.id.tvType);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(listData.get(position) instanceof String){
            return 0;
        }else if(listData.get(position) instanceof Transactions){
            return 1;
        }
        return -1;
    }
}
