package com.example.team32gb.jobit.View.PostedJob;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.JobRecruitmentActivity;
import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.HomeRecruitmentActivity.HomeRecruitmentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DetailPostedJobActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private TextView txtNameCompany,txtNameJob,txtType, txtSalary, txtNumHirse, txtDescription, txtQuatifition;
    private EditText edtNameCompany, edtNameJob, edtDescription, edtQuatifition,edtMinSalary,edtMaxSalary;
    private Spinner spType, spEach,spNumHirse;
    private Button btnEdit, btnSave;
    private LinearLayout lnSalary;

    final ArrayList<String> Type = new ArrayList<String>();
    final ArrayList<String>  Each = new ArrayList<String>();
    final ArrayList<String> NumHires = new ArrayList<String>();

    private String idJob ,idCompany ;
    private String minSalary, maxSalary;

    private DataPostJob data = new DataPostJob() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_posted_job);
        myToolBar = findViewById(R.id.tbDetail);
        myToolBar.setTitle("Chi tiết");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtNameCompany = findViewById(R.id.txtNameCompany);
        txtNameJob = findViewById(R.id.txtNameJob);
        txtType = findViewById(R.id.txtType);
        txtSalary = findViewById(R.id.txtSalary);
        txtNumHirse = findViewById(R.id.txtNumberHirse);
        txtDescription = findViewById(R.id.txtDescription);
        txtQuatifition = findViewById(R.id.txtQualification);

        edtNameCompany = findViewById(R.id.edtNameCompany);
        edtNameJob = findViewById(R.id.edtNameJob);
        edtDescription = findViewById(R.id.edtJobDescription);
        edtQuatifition = findViewById(R.id.edtQualification);
        edtMinSalary = findViewById(R.id.edtMinSalary);
        edtMaxSalary = findViewById(R.id.edtMaxSalary);

        spType = findViewById(R.id.spnJobType);
        spEach = findViewById(R.id.spnEach);
        spNumHirse = findViewById(R.id.spnNumHires);

        lnSalary = findViewById(R.id.lnSalary);

        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);


        Type.add("bán thời gian");
        Type.add("toàn thời gian");
        Type.add("hợp đồng");
        Type.add("thực tập");
        Type.add("tạm thời");

        ArrayAdapter<String> adapterType=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Type);
        adapterType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spType.setAdapter(adapterType);
        spType.setOnItemSelectedListener(this);

        //Xử lý Spinner "Mỗi"
        Each.add("giờ");
        Each.add("ngày");
        Each.add("tuần");
        Each.add("tháng");
        Each.add("năm");
//
//
        ArrayAdapter<String> adapterEach=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Each);
        adapterEach.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spEach.setAdapter(adapterEach);
        spEach.setOnItemSelectedListener(this);
///
        //Xử lý Spinner số lượng người cần tuyển
        NumHires.add("1 người");
        NumHires.add("2 đến 5 người");
        NumHires.add("5 đến 10 người");
        NumHires.add("Hơn 10 người");
        NumHires.add("Nhu cầu liên tục");

        ArrayAdapter<String> adapterNumHires= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,NumHires);
        adapterNumHires.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spNumHirse.setAdapter(adapterNumHires);
        spNumHirse.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        idCompany = bundle.getString("idCompany");
        idJob = bundle.getString("idJob");
        txtNameCompany.setText(bundle.getString("nameCompany"));
        txtNameJob.setText(bundle.getString("nameJob"));
        txtType.setText(bundle.getString("typeJob"));
        minSalary = bundle.getString("minSalary");
        maxSalary = bundle.getString("maxSalary" );
        txtSalary.setText("Từ " + minSalary + " VND đến " + maxSalary +" VND mỗi " + bundle.getString("each"));
        txtNumHirse.setText(bundle.getString("numberEmployer"));
        txtDescription.setText(bundle.getString("description"));
        txtQuatifition.setText(bundle.getString("qualification"));

        btnSave.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

    }
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
        if ( id == R.id.btnEdit){
            btnEdit.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);

            txtNameCompany.setVisibility(View.GONE);
            txtNameJob.setVisibility(View.GONE);
            txtType.setVisibility(View.GONE);
            txtSalary.setVisibility(View.GONE);
            txtNumHirse.setVisibility(View.GONE);
            txtDescription.setVisibility(View.GONE);
            txtQuatifition.setVisibility(View.GONE);

            edtNameCompany.setText(txtNameCompany.getText().toString());
            edtNameJob.setText(txtNameJob.getText().toString());
            edtMinSalary.setText(minSalary);
            edtMaxSalary.setText(maxSalary);
            edtDescription.setText(txtDescription.getText().toString());
            edtQuatifition.setText(txtQuatifition.getText().toString());

            edtNameCompany.setVisibility(View.VISIBLE);
            edtNameJob.setVisibility(View.VISIBLE);
            edtDescription.setVisibility(View.VISIBLE);
            edtQuatifition.setVisibility(View.VISIBLE);
            lnSalary.setVisibility(View.VISIBLE);
            edtMinSalary.setVisibility(View.VISIBLE);
            edtMaxSalary.setVisibility(View.VISIBLE);
            spType.setVisibility(View.VISIBLE);
            spEach.setVisibility(View.VISIBLE);
            spNumHirse.setVisibility(View.VISIBLE);

        }
        if (id == R.id.btnSave){

            btnEdit.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);
            txtNameCompany.setText(edtNameCompany.getText().toString());
            txtNameJob.setText(edtNameJob.getText().toString());
            txtType.setText(Type.get(spType.getSelectedItemPosition()));
            txtSalary.setText("Từ " + edtMinSalary.getText().toString() + " VND đến " + edtMaxSalary.getText().toString() + " VND mỗi " + Each.get(spEach.getSelectedItemPosition()));
            txtNumHirse.setText(NumHires.get(spNumHirse.getSelectedItemPosition()));
            txtDescription.setText(edtDescription.getText().toString());
            txtQuatifition.setText(edtQuatifition.getText().toString());

            txtNameCompany.setVisibility(View.VISIBLE);
            txtNameJob.setVisibility(View.VISIBLE);
            txtType.setVisibility(View.VISIBLE);
            txtSalary.setVisibility(View.VISIBLE);
            txtNumHirse.setVisibility(View.VISIBLE);
            txtDescription.setVisibility(View.VISIBLE);
            txtQuatifition.setVisibility(View.VISIBLE);

            data.setNameJob(txtNameJob.getText().toString());
            data.setTypeJob(txtType.getText().toString());
            data.setMinSalary(edtMinSalary.getText().toString());
            data.setMaxSalary(edtMaxSalary.getText().toString());
            data.setNumberEmployer(txtNumHirse.getText().toString());
            data.setDescription(txtDescription.getText().toString());
            data.setQualification(txtQuatifition.getText().toString());
            data.setEach(Each.get(spEach.getSelectedItemPosition()));

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String str = df.format(c.getTime());
            data.setTime(str);

            edtNameCompany.setText("");
            edtNameJob.setText("");
            edtDescription.setText("");
            edtQuatifition.setText("");
            edtMinSalary.setText("");
            edtMaxSalary.setText("");

            edtNameCompany.setVisibility(View.GONE);
            edtNameJob.setVisibility(View.GONE);
            edtDescription.setVisibility(View.GONE);
            edtQuatifition.setVisibility(View.GONE);
            lnSalary.setVisibility(View.GONE);
            edtMinSalary.setVisibility(View.GONE);
            edtMaxSalary.setVisibility(View.GONE);
            spType.setVisibility(View.GONE);
            spEach.setVisibility(View.GONE);
            spNumHirse.setVisibility(View.GONE);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            DatabaseReference dfData = databaseReference.child("tinTuyenDungs").child(idCompany).child(idJob);
            dfData.setValue(data).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplication(), "Sửa thông tin không thành công",Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplication(), "Sửa thông tin thành công",Toast.LENGTH_LONG).show();
                }
            });

            Config.CHECK_FRAV = 0;
            Intent intent = new Intent(this, JobRecruitmentActivity.class);
            finish();
            startActivity(intent);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
