package com.example.team32gb.jobit.View.SelectUserType;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.HomeJobSeeker.HomeActivity;

import static com.example.team32gb.jobit.Utility.Config.*;

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
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                this.finish();
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
        switch (id) {
            case R.id.btnJobSeeker:
            case R.id.btnDismiss:
                SharedPreferences.Editor editor1 = sharedPreferencesUserType.edit();
                editor1.putInt(USER_TYPE, Config.IS_JOB_SEEKER);
                editor1.apply();
                Intent intent1 = new Intent(this, HomeActivity.class);
                startActivity(intent1);
                this.finish();
                break;
            case R.id.btnEmployer:
                SharedPreferences.Editor editor2 = sharedPreferencesUserType.edit();
                editor2.putInt(USER_TYPE, IS_IMPLOYER);
                editor2.apply();
                Toast.makeText(this, "Employer", Toast.LENGTH_SHORT).show();
//                Intent intent2 = new Intent(this, HomeActivity.class);
//                startActivity(intent2);
                this.finish();
                break;
            case R.id.btnAdmin:
                SharedPreferences.Editor editor3 = sharedPreferencesUserType.edit();
                editor3.putInt(USER_TYPE, IS_IMPLOYER);
                editor3.apply();
                Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
//                Intent intent3 = new Intent(this, HomeActivity.class);
//                startActivity(intent3);
                this.finish();
                break;
            default:
                break;
        }
    }
}
