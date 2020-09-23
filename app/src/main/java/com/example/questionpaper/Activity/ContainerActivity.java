package com.example.questionpaper.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.questionpaper.R;
import com.example.questionpaper.Screens.mytest.MyTestsLandingFragment;
import com.example.questionpaper.Screens.mytest.SelectNoOfMonthsFragment;
import com.example.questionpaper.Screens.mytest.UpComingTestFragment;
import com.google.android.material.navigation.NavigationView;

public class ContainerActivity extends AppCompatActivity {
    private FragmentManager fManager;
    private FragmentTransaction tr;

    // Navigation drawer
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;
    public static RelativeLayout relativeCustomActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_activity);
        relativeCustomActionBar = findViewById(R.id.relativeCustomActionBar);
        // Hide ActionBar
        setNavigationDrawerLayout();

        fManager= getSupportFragmentManager();
        displayFragment(0);
    }
    public void displayFragment(int position){
        switch (position){
            case 0:
                tr=fManager.beginTransaction();
                tr.replace(R.id.containerLayout,new MyTestsLandingFragment());
                tr.addToBackStack(null);
                tr.commit();
                break;

            case 1:
                tr=fManager.beginTransaction();
                tr.replace(R.id.containerLayout,new SelectNoOfMonthsFragment());
                tr.addToBackStack(null);
                tr.commit();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    public void profileIconClick(View v){
        //Toast.makeText(getApplicationContext(),"Icon clicked...",Toast.LENGTH_LONG).show();
        mDrawer.openDrawer(GravityCompat.START);
    }
    private void setNavigationDrawerLayout() {
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // This will display an Up icon (<-), we will replace it with hamburger later
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                //Toast.makeText(getApplicationContext(),"first clicked...",Toast.LENGTH_SHORT).show();
                fragmentClass = MyTestsLandingFragment.class;
                break;
            /*case R.id.nav_second_fragment:
                //fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                //fragmentClass = ThirdFragment.class;
                break;*/
            default:
                fragmentClass = UpComingTestFragment.class;
        }

        if(fragmentClass != null){
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.containerLayout, fragment).commit();
        }
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }



}
