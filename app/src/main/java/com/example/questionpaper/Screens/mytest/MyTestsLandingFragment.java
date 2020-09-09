package com.example.questionpaper.Screens.mytest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.questionpaper.R;
import com.example.questionpaper.Screens.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;

public class MyTestsLandingFragment extends Fragment {
    //@BindView(R.id.tabLayout)
    TabLayout tabLayout;
   // @BindView(R.id.viewPager2)
    ViewPager viewPager;
    MyTestsLandingAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.mytests_landing_fragment,container,false);
        initialiseView(v);
        return v;
    }

    private void initialiseView(View view) {

        tabLayout= view.findViewById(R.id.tabLayout);
        viewPager=view.findViewById(R.id.viewPager2);

        List<String> tabsList=new ArrayList<>();
        tabsList.clear();
        tabsList.add("Upcoming");
        tabsList.add("Live");
        tabsList.add("Completed");
        Log.i("TAG//","tab size:"+tabsList.size());

        viewPager.setOffscreenPageLimit(tabsList.size());
        if(adapter == null){
            adapter=new MyTestsLandingAdapter(getActivity().getSupportFragmentManager(),tabsList);
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}
