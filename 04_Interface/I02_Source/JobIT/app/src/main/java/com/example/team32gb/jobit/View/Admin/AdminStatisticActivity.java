package com.example.team32gb.jobit.View.Admin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.team32gb.jobit.Presenter.AdminStatistic.PresenterAdminStatistic;
import com.example.team32gb.jobit.R;

import androidx.appcompat.app.AppCompatActivity;

public class AdminStatisticActivity extends AppCompatActivity {
    private TextView txtAdminStatisticEmployee;
    private TextView txtAdminStatisticRecruiter;
    private PresenterAdminStatistic presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_statistic);

        txtAdminStatisticEmployee = findViewById(R.id.txtAdminStatisticEmployee);
        txtAdminStatisticRecruiter = findViewById(R.id.txtAdminStatisticRecruiter);

        presenter = new PresenterAdminStatistic();

        long count = presenter.statisticEmployee(txtAdminStatisticEmployee);

        count = presenter.statisticRecruiter(txtAdminStatisticRecruiter);
    }
}
