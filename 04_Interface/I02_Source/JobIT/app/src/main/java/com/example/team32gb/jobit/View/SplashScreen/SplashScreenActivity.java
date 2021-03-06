package com.example.team32gb.jobit.View.SplashScreen;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.SignUpAccountBusiness.SignUpAccountBusiness;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView tvVersion;
    private ProgressBar prgLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prgLoading = findViewById(R.id.prgLoading);
        tvVersion = findViewById(R.id.tv_version);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tvVersion.setText(getString(R.string.tv_version) + " " + packageInfo.versionName);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, SignUpAccountBusiness.class);
                    startActivity(intent);
                }
            }, 1000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
