package com.example.team32gb.jobit.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static void jumpActivity(Context context, Class mclass ) {
        Intent intent = new Intent(context,mclass);
        context.startActivity(intent);
    }
    public static void resetDataLocal(SharedPreferences sharedPreferences) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putInt(Config.USER_TYPE,0);
//        editor.putString(Config.EMAIL_USER,"");
//        editor.putString(Config.PASSWORD_USER,"");
//        editor.putString(Config.NAME_USER,"");
//        editor.putBoolean(Config.IS_LOGGED,false);
//        editor.apply();
    }
    public static String getSubTime(String timeFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            Date date = sdf.parse(timeFormat);
            long time = date.getTime();
            long currentTime =(new Date()).getTime();
            long difference = currentTime - time;
            int subDay = (int) difference/(1000*60*60*24);

            if(subDay > 0) {
                return subDay + " ngày trước";
            } else {
                int subHour = (int)difference/(1000*60*60);
                if(subHour > 0) {
                    return  subHour + " giờ trước";
                } else {
                    int subMinute = (int)difference/(1000*60);
                    return  subMinute + " phút trước";
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void setSubTime(String timeFormat,TextView txtTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        try {
            Date date = sdf.parse(timeFormat);
            long time = date.getTime();
            long currentTime =(new Date()).getTime();
            long difference = currentTime - time;
            int subDay = (int) difference/(1000*60*60*24);

            if(subDay > 0) {
                txtTime.setText(subDay + " ngày trước");
            } else {
                int subHour = (int)difference/(1000*60*60);
                if(subHour > 0) {
                    txtTime.setText(subHour + " giờ trước");
                } else {
                    int subMinute = (int)difference/(1000*60);
                    txtTime.setText(subMinute + " phút trước");
                }
            }

        } catch (ParseException e) {
            txtTime.setText(timeFormat);
            e.printStackTrace();
        }
    }

    public static void signOut(FirebaseAuth firebaseAuth,Context context) {
        firebaseAuth.signOut();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.IS_LOGGED,false);
        editor.apply();
    }
}
