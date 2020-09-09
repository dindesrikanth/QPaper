package com.example.questionpaper.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.questionpaper.Activity.ResultActivity;
import com.example.questionpaper.Activity.TestActivity;
import com.example.questionpaper.ServiceCallbacks;

public class StickyService extends Service {

    // Binder given to clients
    private final IBinder binder = (IBinder) new LocalBinder();
    // Registered callbacks
    private ServiceCallbacks serviceCallbacks;
    private Bundle bundle;

    // Class used for the client Binder.
    public class LocalBinder extends Binder {
        public StickyService getService() {
            // Return this instance of MyService so clients can call public methods
            return StickyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    public void setCallbacks(ServiceCallbacks callbacks) {
        serviceCallbacks = callbacks;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(getClass().getName(), "App just got removed from Recents!");
        Intent intent = new Intent(StickyService.this, TestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (serviceCallbacks != null) {
            Bundle bundle = serviceCallbacks.getLatestTestData();
            intent.putExtras(bundle);
        }
        startActivity(intent);
        stopSelf();
    }


}
