package com.example.team32gb.jobit.View.JobDetail;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.R;

public class DetailJobActivity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private TextView txtDetail;
    private Button btnSave, btnApply;
    private String idJob, idCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailjob);

        myToolBar = findViewById(R.id.tbDetailListJob);
        myToolBar.setTitle("Chi tiết");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtDetail = findViewById(R.id.txtJobDetail);
        btnSave = findViewById(R.id.btnSaveJob);
        btnApply = findViewById(R.id.btnApply);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        idJob = bundle.getString("idJob");
        idCompany = bundle.getString("idCompany");
        Log.e("kiemtraid",idJob + ":" + idCompany);
        txtDetail.setText(bundle.getString("nameJob"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detailjob_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
