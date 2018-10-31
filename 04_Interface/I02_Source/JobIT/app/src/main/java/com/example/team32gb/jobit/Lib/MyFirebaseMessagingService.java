package com.example.team32gb.jobit.Lib;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Test.TestDuyet;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            showNotification(title,body);
        }
    }

    public void showNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, TestDuyet.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,(int) System.currentTimeMillis(),intent,0);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification n = new Notification.Builder(this)
                            .setContentTitle(title)
                            .setContentText(body)
                            .setAutoCancel(false)
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.drawable.ic_avatar_default)
                            .setSound(alarmSound).build();
        notificationManager.notify(0,n);

    }


}
