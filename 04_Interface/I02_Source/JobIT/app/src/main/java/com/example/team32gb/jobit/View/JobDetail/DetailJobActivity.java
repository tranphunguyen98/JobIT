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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailJobActivity extends AppCompatActivity implements View.OnClickListener {
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
        btnApply = findViewById(R.id.btnApply);

        btnApply.setOnClickListener(this);
        myToolBar.setTitle("Chi tiết");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtDetail = findViewById(R.id.txtJobDetail);
        btnSave = findViewById(R.id.btnSaveJob);
        btnApply = findViewById(R.id.btnApply);
//txtDetail
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

    @Override
    public void onClick(View v) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference dfUngVien = databaseReference.child("choDuyets").child(idCompany).child(idJob).child("idUngVien");
        DatabaseReference dfDaApply = databaseReference.child("Applieds").child(FirebaseAuth.getInstance().getUid()).child(idCompany).child("idJob");
        dfDaApply.setValue(idJob);
        dfUngVien.setValue(FirebaseAuth.getInstance().getUid());
        Toast.makeText(this, "apply thanh cong", Toast.LENGTH_SHORT).show();
    }
}
