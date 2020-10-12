package com.example.questionpaper.Common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;

import java.util.regex.Pattern;

public class Utility {
    public static Utility instance = null;
    public static int my_test_months_data=1;
    public static String[] listOfMonths={"1","3","6","9","12"};
    public static String[] listOfGender={"Male","Female"};
    public static String[] listOfStates={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh",
    "Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharastra","Manipur","Meghalaya","Mizoram","Nagaland",
            "Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Trippura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar Islands",
            "Chandigarh","Dadra and Nagar Haveli and Daman and Diu","Delhi","Jammu and Kashmir","Ladakh","Lakshadweep","Puducherry"};
    public static final int ACTION_DONE = 1;

    // User info fragment
    public static String password = "";

    private Utility(){

    }
    public static Utility getInstance(){
       return instance = new Utility();
    }

    public static ProgressDialog getProgressDialog(Context context){
        ProgressDialog pDialog=new ProgressDialog(context);
        pDialog.setMessage("loading....");
        //pDialog.setTitle("Progress Dialog");
        //pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(false);
        return pDialog;
    }


       public static AlertDialog.Builder showAlertDialog(Context context,String title,String message){
           AlertDialog.Builder b=new AlertDialog.Builder(context);
           b.setCancelable(false);
           b.setTitle(title);
           b.setMessage(message);
           DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                    if(i == dialogInterface.BUTTON_POSITIVE){
                        dialogInterface.dismiss();
                    }
               }
           };
           b.setPositiveButton("OK",listener);
           b.setNegativeButton("Cancel",listener);
           b.show();
           return b;
       }

    public static void removeAllFragments(Activity  activity) {
           FragmentManager fm = activity.getFragmentManager();
           int count = fm.getBackStackEntryCount();
           FragmentTransaction tr=fm.beginTransaction();
           while (count >0){
               fm.popBackStack();
               fm.executePendingTransactions();
               count--;
           }
           tr.commitAllowingStateLoss();
    }
    public void moveToFragment(int containerLayout, Fragment fragment, Activity activity){
        try{
            FragmentManager fragmentManager = activity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            navigateToFragment(containerLayout,fragmentManager,fragmentTransaction,fragment);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void navigateToFragment(int fragment_container, FragmentManager fragmentManager, FragmentTransaction fragmentTransaction, Fragment fragment) {
        if(fragment!=null){
            String backStateName = fragment.getClass().getName();
            String fragmentTag = backStateName;
            if(fragmentManager!=null && fragmentTransaction!=null){
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName,0);
                if(!fragmentPopped){
//                    fragmentTransaction.replace(fragment_container,fragment);
                    fragmentTransaction.addToBackStack(backStateName);
                    fragmentTransaction.commit();
                }
            }
        }
    }

    public static boolean isValidEmail(String email){
       /// email = "contribute@geeksforgeeks.org";
        if (email == null)
            return false;
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pat = Pattern.compile(EMAIL_REGEX);

        return pat.matcher(email).matches();
    }

}
