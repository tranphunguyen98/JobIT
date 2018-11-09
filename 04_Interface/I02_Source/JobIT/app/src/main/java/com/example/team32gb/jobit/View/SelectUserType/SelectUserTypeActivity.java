package com.example.team32gb.jobit.View.SelectUserType;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.Utility.Util;
import com.example.team32gb.jobit.View.Admin.AdminHomeActivity;
import com.example.team32gb.jobit.View.HomeJobSeeker.HomeJobSeekerActivity;
import com.example.team32gb.jobit.View.HomeRecruitmentActivity.HomeRecruitmentActivity;
import com.example.team32gb.jobit.View.SignIn.SignInActivity;

import static com.example.team32gb.jobit.Utility.Config.IS_LOGGED;
import static com.example.team32gb.jobit.Utility.Config.IS_RECRUITER;
import static com.example.team32gb.jobit.Utility.Config.SHARED_PREFERENCES_NAME;
import static com.example.team32gb.jobit.Utility.Config.USER_TYPE;

public class SelectUserTypeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnJobSeeker, btnEmployer, btnAdmin, btnDismiss;
    private SharedPreferences sharedPreferencesUserType;
    private int userType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        btnJobSeeker = findViewById(R.id.btnJobSeeker);
        btnEmployer = findViewById(R.id.btnEmployer);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnDismiss = findViewById(R.id.btnDismiss);

        btnJobSeeker.setOnClickListener(this);
        btnEmployer.setOnClickListener(this);
        btnAdmin.setOnClickListener(this);
        btnDismiss.setOnClickListener(this);
        sharedPreferencesUserType = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        userType = sharedPreferencesUserType.getInt(USER_TYPE, 0);

        switch (userType) {
            case Config.IS_JOB_SEEKER:
                Util.jumpActivity(this, HomeJobSeekerActivity.class);
                this.finish();
                break;
            case Config.IS_RECRUITER:
                if (sharedPreferencesUserType.getBoolean(IS_LOGGED, false)) {
                    Util.jumpActivity(SelectUserTypeActivity.this, HomeRecruitmentActivity.class);
                } else {
                    Util.jumpActivity(SelectUserTypeActivity.this, SignInActivity.class);
                }
                this.finish();
                break;
            case Config.IS_ADMIN:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        SharedPreferences.Editor editor = sharedPreferencesUserType.edit();
        switch (id) {
            case R.id.btnJobSeeker:
            case R.id.btnDismiss:
                editor.putInt(USER_TYPE, Config.IS_JOB_SEEKER);
                editor.apply();
                Util.jumpActivity(SelectUserTypeActivity.this, HomeJobSeekerActivity.class);
                this.finish();
                break;
            case R.id.btnEmployer:
                editor.putInt(USER_TYPE, IS_RECRUITER);
                editor.apply();
                if (sharedPreferencesUserType.getBoolean(IS_LOGGED, false)) {
                    Util.jumpActivity(SelectUserTypeActivity.this, HomeRecruitmentActivity.class);
                } else {
                    Util.jumpActivity(SelectUserTypeActivity.this, SignInActivity.class);
                }
                this.finish();
                break;
            case R.id.btnAdmin:
                editor.putInt(USER_TYPE, IS_RECRUITER);
                editor.apply();
                Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();

                Intent intent3 = new Intent(this, AdminHomeActivity.class);
                startActivity(intent3);

                this.finish();
                break;
            default:
                break;
        }
    }
}
