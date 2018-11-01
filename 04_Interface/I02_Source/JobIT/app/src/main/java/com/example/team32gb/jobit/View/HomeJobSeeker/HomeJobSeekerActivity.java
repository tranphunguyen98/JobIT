package com.example.team32gb.jobit.View.HomeJobSeeker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.Utility.Util;
import com.example.team32gb.jobit.View.ChangePassword.ChangePasswordActivity;
import com.example.team32gb.jobit.View.CreateCV.CreateCVActivity;
import com.example.team32gb.jobit.View.JobSeekerProfile.JobSeekerProfileActivity;
import com.example.team32gb.jobit.View.MyJob.MyJobActivity;
import com.example.team32gb.jobit.View.SelectUserType.SelectUserTypeActivity;
import com.example.team32gb.jobit.View.SignIn.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class HomeJobSeekerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTimViec;
    private Button btnSignIn;
    private Button btnCreateCV;
    private Button btnMyJob;
    private Button btnAccount;
    private Button btnSignOut;
    private Button btnChangeUserType;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSignIn = findViewById(R.id.btnDangNhap);
        btnCreateCV = findViewById(R.id.btnCV);
        btnTimViec = findViewById(R.id.btnTimViec);
        btnMyJob = findViewById(R.id.btnMyJob);
        btnAccount = findViewById(R.id.btnAccount);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnChangeUserType = findViewById(R.id.btnChangeUserType);

        firebaseAuth = FirebaseAuth.getInstance();


        btnTimViec.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnCreateCV.setOnClickListener(this);
        btnMyJob.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnChangeUserType.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (firebaseAuth.getCurrentUser() != null) {
            btnSignIn.setVisibility(View.GONE);
            btnAccount.setVisibility(View.VISIBLE);
        } else {
            btnAccount.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangNhap:
                Intent intentSI = new Intent(this, SignInActivity.class);
                startActivity(intentSI);
                break;
            case R.id.btnCV:
                Intent intentCCV = new Intent(this, CreateCVActivity.class);
                startActivity(intentCCV);
                break;
            case R.id.btnMyJob:
                Util.gotoActivity(this,MyJobActivity.class);
                break;
            case R.id.btnSignOut:
                firebaseAuth.signOut();
                btnAccount.setVisibility(View.GONE);
                btnSignIn.setVisibility(View.VISIBLE);
                break;
            case R.id.btnAccount:
                Intent intentAc = new Intent(this, JobSeekerProfileActivity.class);
                startActivity(intentAc);
                break;
            case R.id.btnChangeUserType:
                //Intent intentCh = new Intent(this, .class);
//                startActivity(intentCh);
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Config.USER_TYPE,0);
                editor.apply();
                Util.gotoActivity(this,SelectUserTypeActivity.class);
                break;
            default:
                break;
        }

    }
}
