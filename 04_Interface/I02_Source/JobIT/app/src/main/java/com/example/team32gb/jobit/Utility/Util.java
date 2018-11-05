package com.example.team32gb.jobit.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

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
}
