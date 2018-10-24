package com.example.team32gb.jobit.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class Util {
    public static void gotoActivity(Context context,Class mclass ) {
        Intent intent = new Intent(context,mclass);
        context.startActivity(intent);
    }
}
