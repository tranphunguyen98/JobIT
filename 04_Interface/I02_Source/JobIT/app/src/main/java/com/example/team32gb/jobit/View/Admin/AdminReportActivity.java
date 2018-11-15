package com.example.team32gb.jobit.View.Admin;

import android.os.Bundle;

import com.example.team32gb.jobit.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AdminReportActivity extends AppCompatActivity {
    TabLayout tlReport;
    ViewPager vpReport;

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
