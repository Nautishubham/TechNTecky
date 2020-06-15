package com.techno.techntecky.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techno.techntecky.MainActivity;
import com.techno.techntecky.R;

import java.util.Map;
import java.util.Random;

public class myfirebaseinstanceservices extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().isEmpty()){
            shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

               }else {
            shownotifiaction(remoteMessage.getData());
        }



    }

    private  void shownotifiaction(Map<String ,String>data)
    {
        String title=data.get("title").toString();
        String body=data.get("body").toString();
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String notificationchannelid="techno.techntecky.services.test";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(notificationchannelid,"Notification"
            ,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Code sphere");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);


        }
        NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this,notificationchannelid);
        notificationbuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(),notificationbuilder.build());

    }

    private void shownotification(String title,String body)
    {
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String notificationchannelid="techno.techntecky.services.test";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(notificationchannelid,"Notification"
                    ,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Code sphere");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);


        }
        Intent resultintent=new Intent(this, MainActivity.class);
        PendingIntent reultpending=PendingIntent.getActivity(this,1,resultintent,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this,notificationchannelid);
        notificationbuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(reultpending)
                .setAutoCancel(true)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(),notificationbuilder.build());

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("TOKENFIREBASE",s);
    }
}
