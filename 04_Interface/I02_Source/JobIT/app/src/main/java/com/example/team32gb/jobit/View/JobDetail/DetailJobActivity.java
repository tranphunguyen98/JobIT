package com.example.team32gb.jobit.View.JobDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.Utility.Util;
import com.example.team32gb.jobit.View.CompanyDetail.CompanyDetailActivity;
import com.example.team32gb.jobit.View.CreateCV.CreateCVActivity;
import com.example.team32gb.jobit.View.PostJob.PostJobRecruitmentActivity;
import com.example.team32gb.jobit.View.SignIn.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailJobActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private TextView txtNameJob, txtTime, txtSalary, txtTypeJob, txtNumberOfCadidates, txtJobDescription, txtJobRequierment;
    private Button btnSave, btnApply;
    private String idJob, idCompany;
    private Button btnAvatar,btnEdit;
    private ProgressDialog progressDialog;
    private LinearLayout lnApply,lnEdit;
    private DatabaseReference nodeRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailjob);

        myToolBar = findViewById(R.id.tbDetailListJob);
        btnApply = findViewById(R.id.btnApply);
        btnEdit = this.findViewById(R.id.btnEditJob);


        btnApply.setOnClickListener(this);
        myToolBar.setTitle("Chi tiết");
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        lnApply = findViewById(R.id.lnApply);
        lnEdit = findViewById(R.id.lnEdit);

        final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(Config.IS_ACTIVITY_APPLY, false)) {
            lnApply.setVisibility(View.GONE);
        }
        if (sharedPreferences.getInt(Config.USER_TYPE, 0) == Config.IS_RECRUITER) {
            lnEdit.setVisibility(View.VISIBLE);
            lnApply.setVisibility(View.INVISIBLE);
        } else {
            lnEdit.setVisibility(View.GONE);
        }

        txtNameJob = findViewById(R.id.txtNameJobDetail);
        txtTime = findViewById(R.id.txtTime);
        txtSalary = findViewById(R.id.txtSalaryDetail);
        txtTypeJob = findViewById(R.id.txtTypeJob);
        txtNumberOfCadidates = findViewById(R.id.txtNumberOfCandidates);
        txtJobDescription = findViewById(R.id.txtJobDescription);
        txtJobRequierment = findViewById(R.id.txtJobRequirements);

        btnSave = findViewById(R.id.btnSaveJob);
        btnApply = findViewById(R.id.btnApply);
        btnAvatar = findViewById(R.id.imgAvatarCompany);

        btnSave.setOnClickListener(this);
        btnApply.setOnClickListener(this);
        btnAvatar.setOnClickListener(this);



        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        idJob = bundle.getString("idJob");
        idCompany = bundle.getString("idCompany");

        nodeRoot = FirebaseDatabase.getInstance().getReference();

        nodeRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                DataSnapshot dsJob = dataSnapshot.child("tinTuyenDungs").child(idCompany).child(idJob);
                if (sharedPreferences.getBoolean(Config.IS_LOGGED, false)) {
                    DataSnapshot dsApplied = dataSnapshot.child("Applieds").child(FirebaseAuth.getInstance().getUid());

                    if (dsApplied.hasChild(idCompany)) {
                        if (dsApplied.child(idCompany).hasChild(idJob)) {
                            btnApply.setEnabled(false);
                            btnApply.setTextColor(getResources().getColor(R.color.gray));
                            btnApply.setText("Applied");
                            btnApply.setBackground(getResources().getDrawable(R.drawable.custom_background_button_save_applied));
                        }
                    }
                }

                final DataPostJob dataPostJob = dsJob.getValue(DataPostJob.class);
                txtNameJob.setText(dataPostJob.getNameJob());
                txtSalary.setText("Từ $" + dataPostJob.getMinSalary() + " đến $" + dataPostJob.getMaxSalary() + "/" + dataPostJob.getEach());
                txtTypeJob.setText(dataPostJob.getTypeJob());
                txtNumberOfCadidates.setText(dataPostJob.getNumberEmployer());
                txtJobDescription.setText(dataPostJob.getDescription());
                txtJobRequierment.setText(dataPostJob.getQualification());
                Util.setSubTime(dataPostJob.getTime(), txtTime);
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(DetailJobActivity.this,PostJobRecruitmentActivity.class);
                        intent1.putExtra("detail",dataPostJob);
                        intent1.putExtra("idJob",idJob);
                        startActivity(intent1);
                    }
                });
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        int id = v.getId();

        switch (id) {
            case R.id.btnApply:
                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                if (sharedPreferences.getBoolean(Config.IS_LOGGED, false)) {
                    final String idJobseeker = FirebaseAuth.getInstance().getUid();
                    nodeRoot.child("cvs").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(idJobseeker)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                                String currentDate = sdf.format(new Date());

                                DatabaseReference dfTimeApplied = nodeRoot.child("choDuyets").child(idCompany).child(idJob).child(idJobseeker).child("timeApplied");
                                dfTimeApplied.setValue(currentDate);

                                DatabaseReference dfDaApply = nodeRoot.child("Applieds").child(idJobseeker).child(idCompany).child(idJob).child("timeAppiled");
                                dfDaApply.setValue(currentDate);
                                Toast.makeText(DetailJobActivity.this, "Apply thành công", Toast.LENGTH_SHORT).show();

                                btnApply.setEnabled(false);
                                btnApply.setTextColor(getResources().getColor(R.color.gray));
                                btnApply.setText("Applied");
                                btnApply.setBackground(getResources().getDrawable(R.drawable.custom_background_button_save_applied));
                            } else {
                                Util.jumpActivity(DetailJobActivity.this,CreateCVActivity.class);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                } else {
                    Util.jumpActivity(DetailJobActivity.this, SignInActivity.class);
                }
                break;
            case R.id.btnSaveJob:
                break;
            case R.id.imgAvatarCompany:
                Intent intent = new Intent(this, CompanyDetailActivity.class);
                intent.putExtra("idCompany", idCompany);
                startActivity(intent);
                break;

            default:
                break;
        }

    }
}
