package com.example.questionpaper.Common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utility {

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

}
