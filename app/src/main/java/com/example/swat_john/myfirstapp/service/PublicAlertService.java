package com.example.swat_john.myfirstapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Swat-John on 3/27/2017.
 */

public class PublicAlertService extends Service{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {
        Toast.makeText(PublicAlertService.this, "Service Started", Toast.LENGTH_SHORT).show();
//        return super.onStartCommand(intent,flags,startId);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        Toast.makeText(PublicAlertService.this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
