package com.example.team32gb.jobit.Lib;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("kiemtramessage", "from: " + remoteMessage.getFrom());
//        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null) {
            Log.e("kiemtramessage",remoteMessage.getNotification().getBody() + ":" + remoteMessage.getNotification().getTitle());
        }
    }


}
