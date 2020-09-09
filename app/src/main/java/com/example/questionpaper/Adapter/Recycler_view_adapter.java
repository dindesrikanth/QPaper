package com.example.questionpaper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

public class Recycler_view_adapter extends RecyclerView.Adapter<Recycler_view_adapter.ViewHolder> {
        private List<Questionesmodel> item;
    private Context context;
    RadioButton checkedRadioButton = null;
    private OnItemClickListener mListener;
    private int currentPosition;

    public int getPosition() {
        return currentPosition;
    }

    public interface OnItemClickListener {
        void optionsclick(int checkid, int position);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Recycler_view_adapter(Context context, List<Questionesmodel> item) {
        this.item = item;
        this.context = context;
    }

    public void updateList(List<Questionesmodel> item) {
        this.item = item;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        vh = new Recycler_view_adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Long srno = item.get(position).getQues_local_id();
        holder.radiogrp.setOnCheckedChangeListener(null);
        holder.txt_quetion.setText(srno + "." + item.get(position).getQues_detail());
        holder.radio1.setText(item.get(position).getOpta());
        holder.radio2.setText(item.get(position).getOptb());
        holder.radio3.setText(item.get(position).getOptc());
        holder.radio4.setText(item.get(position).getOptd());
        currentPosition = position;
//        if (item.get(position).getSelectedOption() != 0) {
//            checkedRadioButton = holder.radiogrp.findViewById(item.get(position).getSelectedOption());
//            checkedRadioButton.setChecked(true);
//        } else {
//            holder.radiogrp.clearCheck();
//        }
        holder.radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
                mListener.optionsclick(checkedId, position);
            }
        });


        holder.btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.optionsclick(0, position);
//                if (item.get(position).getSelectedOption() == 0) {
//                    item.get(position).setQues_Status(1);
//
//                }

            }
        });
    }

    @Override
    public int getItemCount() {
        Integer j = item.size();
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_quetion, responcetxt, responcetxt2;
        public RadioGroup radiogrp;
        RadioButton radio1, radio2, radio3, radio4, radio5;
        public View lyt_parent;
        Button btn_Clear, btn_mark;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("autolog", "ViewHolder");
            responcetxt = (TextView) itemView.findViewById(R.id.responcetxt);
            responcetxt2 = (TextView) itemView.findViewById(R.id.responcetxt2);
            txt_quetion = (TextView) itemView.findViewById(R.id.quest);
            radiogrp = (RadioGroup) itemView.findViewById(R.id.radio_group);
            radio1 = (RadioButton) itemView.findViewById(R.id.A);
            radio2 = (RadioButton) itemView.findViewById(R.id.B);
            radio3 = (RadioButton) itemView.findViewById(R.id.C);
            radio4 = (RadioButton) itemView.findViewById(R.id.D);
            radio5 = (RadioButton) itemView.findViewById(R.id.E);
            btn_Clear = (Button) itemView.findViewById(R.id.clear);
            btn_mark = (Button) itemView.findViewById(R.id.mark);
        }
    }
}

