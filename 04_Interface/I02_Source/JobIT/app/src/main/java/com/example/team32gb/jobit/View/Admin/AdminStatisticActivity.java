package com.example.team32gb.jobit.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.team32gb.jobit.Presenter.AdminStatistic.PresenterAdminStatistic;
import com.example.team32gb.jobit.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminStatisticActivity extends AppCompatActivity {
    private TextView txtAdminStatisticEmployee;
    private TextView txtAdminStatisticRecruiter;
    private PresenterAdminStatistic presenter;

    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_statistic);

        txtAdminStatisticEmployee = findViewById(R.id.txtAdminStatisticEmployee);
        txtAdminStatisticRecruiter = findViewById(R.id.txtAdminStatisticRecruiter);

        toolbar = findViewById(R.id.tbAdminStatistic);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        presenter = new PresenterAdminStatistic();

        long count = presenter.statisticEmployee(txtAdminStatisticEmployee);

        count = presenter.statisticRecruiter(txtAdminStatisticRecruiter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeAdmin:
                Intent intent = new Intent(this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
