package com.example.questionpaper.Screens.mytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.questionpaper.Screens.mytest.Completed.CompletedTestsFragment;
import com.example.questionpaper.Screens.mytest.Live.LiveTestFragment;
import com.example.questionpaper.Screens.mytest.UpComing.UpComingTestFragment;

import java.util.ArrayList;
import java.util.List;

public class MyTestsLandingAdapter  extends FragmentPagerAdapter{

    private List<Fragment> mFragmentList;
    private List<String> tabList;
    FragmentManager fragmentManager;

    public MyTestsLandingAdapter(FragmentManager fragmentManager,List<String> tabList){
        super(fragmentManager);
        this.tabList = tabList;
        this.fragmentManager=fragmentManager;
        mFragmentList = new ArrayList<>(tabList.size());
        fillFragmentWithTabsTitle();
    }

    private void fillFragmentWithTabsTitle() {
       for (String tabName:tabList){
           if(tabName.contains("Upcoming")){
              mFragmentList.add(new UpComingTestFragment());
           }else if(tabName.contains("Live")){
               mFragmentList.add(new LiveTestFragment());
           }else if(tabName.contains("Completed")){
               mFragmentList.add(new CompletedTestsFragment());
           }
       }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
