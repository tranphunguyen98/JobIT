package com.example.team32gb.jobit.View.HomeJobSeeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.team32gb.jobit.View.CreateCV.CreateCVActivity;
import com.example.team32gb.jobit.View.ChangePassword.ChangePasswordActivity;
import com.example.team32gb.jobit.View.JobSeekerProfile.JobSeekerProfileActivity;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.SignIn.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTimViec;
    private Button btnSignIn;
    private Button btnCreateCV;
    private Button btnMyJob;
    private Button btnAccount;
    private Button btnSignOut;
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
        firebaseAuth = FirebaseAuth.getInstance();



        btnTimViec.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnCreateCV.setOnClickListener(this);
        btnMyJob.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnAccount.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("kiemtra","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("kiemtra","onResume");
        if(firebaseAuth.getCurrentUser() != null) {
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
        Log.e("kiemtra","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("kiemtra","onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("kiemtra","Restart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("kiemtra","Destroy");

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
                Intent intentMJ = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentMJ);
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
            default:
                break;
        }

    }
}
