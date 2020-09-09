package com.example.questionpaper.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.R;

import java.util.List;

public class Get_test_header_adapter extends RecyclerView.Adapter<Get_test_header_adapter.CategoryViewHolder>{
    private List<Get_user_test> testlist;
    private List<String> courselist;
    Context context;
    private  int selectedIndex;
    View selectView;
    private callback listener;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    public interface callback{
        void onTitleClicked(int position);
    }
    public Get_test_header_adapter(List<String> courselist, Context context,callback listener) {
        this.courselist = courselist;
        this.context = context;
        this.listener = listener;
        selectedIndex = 0;
    }

    @NonNull
    @Override
    public Get_test_header_adapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_usertest_header_view, parent, false);
        Get_test_header_adapter.CategoryViewHolder categoryViewHolder = new Get_test_header_adapter.CategoryViewHolder(categoryProductView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Get_test_header_adapter.CategoryViewHolder holder, final int position) {
         String course = courselist.get(position);
holder.couse_name.setText(course);
      /*  holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.clicker.setBackgroundColor(context.getResources().getColor(R.color.skyblue));
                holder.clicker.setVisibility(View.VISIBLE);
                if(selectedIndex != position){
                    //holder.clicker.setBackgroundColor(Color.TRANSPARENT);
                   holder.clicker.setVisibility(View.INVISIBLE);
                }
               selectView = holder.clicker;
                selectedIndex = position;
               listener.onTitleClicked(position);
            }
        });*/
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.transparent));

        if (selectedItem == position) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int previousItem = selectedItem;
                selectedItem = position;

                notifyItemChanged(previousItem);
                notifyItemChanged(position);

                listener.onTitleClicked(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courselist.size();
    }
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        TextView couse_name;
        View clicker;
       // CardView cardview;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            couse_name = itemView.findViewById(R.id.coursename);
            clicker = itemView.findViewById(R.id.clicker);
            cardView = itemView.findViewById(R.id.cardview);
            //itemView.setOnClickListener(new View.OnClickListener() {
              //  @Override
               // public void onClick(View v) {
                //    clicker.setBackgroundColor(Color.BLUE);
              //  }
           // });
        }

    }
}
