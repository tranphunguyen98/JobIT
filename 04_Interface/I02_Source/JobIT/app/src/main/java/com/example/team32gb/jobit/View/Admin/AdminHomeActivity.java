package com.example.team32gb.jobit.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.team32gb.jobit.R;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnReport;
    Button btnApproval;
    Button btnStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btnReport = findViewById(R.id.btnReport);
        btnApproval = findViewById(R.id.btnApproval);
        btnStatistic = findViewById(R.id.btnStatistic);

        btnReport.setOnClickListener(this);
        btnApproval.setOnClickListener(this);
        btnStatistic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnReport:
                //TODO
                intent = new Intent();
                intent.setClass(this, AdminReportActivity.class);
                startActivity(intent);
                break;

            case R.id.btnApproval:
                //TODO
                intent = new Intent();
                intent.setClass(this, AdminApprovalActivity.class);
                startActivity(intent);
                break;
            case R.id.btnStatistic:
                //TODO
                break;

            default:
                break;


        }

    }
}
