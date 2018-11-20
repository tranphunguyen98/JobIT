package com.example.team32gb.jobit.Lib;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.team32gb.jobit.ListCandidateAcvitity;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Test.TestDuyet;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.annotation.RequiresApi;

import static android.app.NotificationManager.*;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Map<String, String> dataMap = remoteMessage.getData();
            Bundle bundle = new Bundle();
            String type = dataMap.get("type");
            String idCompany = dataMap.get("idCompany");
            String idJob = dataMap.get("idJob");
            String nameJob = dataMap.get("nameJob");
            String timeJob = dataMap.get("timeJob");

            Log.e("kiemtraFCM", idCompany + " : " + idJob + " : " + nameJob + " : " + timeJob);
            bundle.putString("idCompany", dataMap.get("idCompany"));
            bundle.putString("idJob", dataMap.get("idJob"));
            bundle.putString("nameJob", dataMap.get("nameJob"));
            bundle.putString("timeJob", dataMap.get("timeJob"));
            Log.e("kiemtraFCM", type + " : " + title + " : " + body);
            showNotification(title, body, type, bundle);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(NotificationManager notificationManager) {
        NotificationChannel edmtChannel = null;
        edmtChannel = new NotificationChannel("com.example.team32gb.jobit.Lib.MyFirebaseMessagingService", "Jobit", IMPORTANCE_DEFAULT);
        edmtChannel.enableLights(true);
        edmtChannel.enableVibration(true);
        edmtChannel.setLightColor(Color.GREEN);
        edmtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(edmtChannel);
    }

    public void showNotification(String title, String body, String type, Bundle bundle) {
        String channelId = "channelId";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent;
        if (type.equals("thongBaoUngVienApply")) {
            intent = new Intent(this, ListCandidateAcvitity.class);
            intent.putExtra("bundle", bundle);
        } else {
            intent = new Intent(this, TestDuyet.class);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel edmtChannel = null;
            edmtChannel = new NotificationChannel(channelId, "Jobit", IMPORTANCE_DEFAULT);
            edmtChannel.enableLights(true);
            edmtChannel.enableVibration(true);
            edmtChannel.setLightColor(Color.GREEN);
            edmtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(edmtChannel);

            builder = new Notification.Builder(this)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.icon_app)
                    .setChannelId(channelId)
                    .setSound(alarmSound);
        } else {
            builder = new Notification.Builder(this)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.icon_app)
                    .setSound(alarmSound);
        }


        notificationManager.notify(113, builder.build());

    }


}
