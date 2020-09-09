package com.example.questionpaper.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {
    private List<String> optionText;
    private Context context ;
    public OptionAdapter(List<String> optionText, Context context) {
        this.optionText = optionText;
        this.context = context;
    }

    @Override
    public OptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("autolog", "onCreateViewHolder");
        OptionAdapter.ViewHolder vh;
        View view = LayoutInflater.from(context).inflate(R.layout.test_option,  parent, false);
        vh = new OptionAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final OptionAdapter.ViewHolder holder, final int position) {
        holder.test_option_count.setText(position);
        holder.test_option_text.setText(optionText.get(position));
    }
    @Override
    public int getItemCount() {
        return optionText.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
                //public TextView ques, hobby;


                TextView test_option_count, test_option_text;
                public ViewHolder(View itemView) {
                    super(itemView);
                    test_option_count = (TextView) itemView.findViewById(R.id.test_option_count) ;
                    test_option_text = (TextView) itemView.findViewById(R.id.test_option_text) ;
                }

            }

    }