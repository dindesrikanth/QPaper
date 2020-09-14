package com.example.questionpaper.Screens.mytest;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.UpComing.Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpComingTestAdapter extends BaseExpandableListAdapter {
    UpComingTestFragment upComingTestFragment;
    List<Tests> tests;
    ArrayList<String> courseName;
    HashMap<String,List<Tests>> courseItems;

    /*public UpComingTestAdapter(UpComingTestFragment upComingTestFragment, List<Tests> tests){
        this.upComingTestFragment=upComingTestFragment;
        this.tests=tests;
    }
*/

    public UpComingTestAdapter(UpComingTestFragment upComingTestFragment, ArrayList courseName, HashMap<String, List<Tests>> courseItems) {
        this.upComingTestFragment=upComingTestFragment;
        this.courseName = courseName;
        this.courseItems = courseItems;
    }

    @Override
    public int getGroupCount() {
        return courseName.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.courseItems.get(this.courseName.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.courseName.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.courseItems.get(this.courseName.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String)getGroup(groupPosition);
        View view =null ;
        if(convertView == null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coursename_layout,parent,false);
            TextView courseName = (TextView) view.findViewById(R.id.tvCourseName);
            courseName.setText(group);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child = (String)getGroup(groupPosition);
        View view =null ;
        if(convertView == null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coursitems_layout,parent,false);
            TextView time = (TextView) view.findViewById(R.id.tvTime);
            TextView date = (TextView) view.findViewById(R.id.tvDate);
            TextView tvTestName = (TextView) view.findViewById(R.id.tvTestName);
            TextView tvDurution = (TextView) view.findViewById(R.id.tvDurution);
            time.setText(this.courseItems.get(courseName.get(groupPosition)).get(childPosition).getTestTime());
            date.setText(this.courseItems.get(courseName.get(groupPosition)).get(childPosition).getDate());
            tvTestName.setText(this.courseItems.get(courseName.get(groupPosition)).get(childPosition).getName());
            tvDurution.setText(this.courseItems.get(courseName.get(groupPosition)).get(childPosition).getDuration());
        }
        return view;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
