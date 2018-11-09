package com.example.team32gb.jobit.View.Admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.team32gb.jobit.R;

public class AdminReportActivity extends AppCompatActivity {
    TabLayout tlReport;
    ViewPager vpReport;
    //todo adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);

        tlReport = findViewById(R.id.tlReport);
        vpReport = findViewById(R.id.vpReport);

        AdminReportAdapterFragment adapter = new AdminReportAdapterFragment(this, getSupportFragmentManager());


        vpReport.setAdapter(adapter);
        tlReport.setupWithViewPager(vpReport);
    }
}
