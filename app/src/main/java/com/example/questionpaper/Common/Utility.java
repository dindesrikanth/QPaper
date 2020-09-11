package com.example.questionpaper.Common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;

public class Utility {
    public static Utility instance = null;

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
}
