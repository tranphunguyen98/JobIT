package com.example.team32gb.jobit.View.PostJob;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import android.widget.Spinner;

import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Presenter.PostJob.PresenterInPostJob;
import com.example.team32gb.jobit.Presenter.PostJob.PresenterPostJob;
import com.example.team32gb.jobit.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PostJobRecruitmentActivity extends AppCompatActivity implements ViewPostJob,AdapterView.OnItemSelectedListener {
    private Toolbar myToolBar;
    private ActionBar actionBar;

    private EditText edtJobTitle;
    private Spinner spnJobType;
    private EditText edtMinSalary;
    private EditText edtMaxSalary;
    private EditText edtCompanyTitle;
    private Spinner spnEach;
    private Spinner spnNumHires;
    private EditText edtJobDecription;
    private EditText edtQualification;
    private Button btnPost;

    final ArrayList<String> Type = new ArrayList<String>();
    final ArrayList<String>  Each = new ArrayList<String>();
    final ArrayList<String> NumHires = new ArrayList<String>();


    private PresenterInPostJob presenter;
    private DataPostJob dataPostJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job_recruitment);

        myToolBar = findViewById(R.id.tbJobRecruitment);
        myToolBar.setTitle("Danh sách việc làm ");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        edtCompanyTitle=findViewById(R.id.edtCompanyTittle);
        edtJobTitle=findViewById(R.id.edtJobTittle);
        spnJobType=findViewById(R.id.spnJobType);
        edtMinSalary=findViewById(R.id.edtMinSalary);
        edtMaxSalary=findViewById(R.id.edtMaxSalary);
        spnEach=findViewById(R.id.spnEach);
        spnNumHires=findViewById(R.id.spnNumHires);
        edtJobDecription=findViewById(R.id.edtJobDescription);
        edtQualification=findViewById(R.id.edtQualification);
        btnPost=findViewById(R.id.btnPost);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //xử lys spinner loại công việc
        Type.add("bán thời gian");
        Type.add("toàn thời gian");
        Type.add("hợp đồng");
        Type.add("thực tập");
        Type.add("tạm thời");

        ArrayAdapter<String> adapterType=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Type);
        adapterType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnJobType.setAdapter(adapterType);
        spnJobType.setOnItemSelectedListener(this);

        //Xử lý Spinner "Mỗi"
        Each.add("giờ");
        Each.add("ngày");
        Each.add("tuần");
        Each.add("tháng");
        Each.add("năm");


        ArrayAdapter<String> adapterEach=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Each);
        adapterEach.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnEach.setAdapter(adapterEach);
        spnEach.setOnItemSelectedListener(this);

        //Xử lý Spinner số lượng người cần tuyển
        NumHires.add("1 người");
        NumHires.add("2 đến 5 người");
        NumHires.add("5 đến 10 người");
        NumHires.add("Hơn 10 người");
        NumHires.add("Nhu cầu liên tục");

        ArrayAdapter<String> adapterNumHires= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,NumHires);
        adapterNumHires.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNumHires.setAdapter(adapterNumHires);
        spnNumHires.setOnItemSelectedListener(this);


        //  presenter = new PresenterPostJob(this);
        //Xử lý Button Đăng
        //Test button k được nên phải comment lại
        // boolean valid=false;
//        if(edtJobTitle.getText().toString().length()<=0){
//            edtJobTitle.setError("Chưa nhập tên công việc");
//        }
//        if(Integer.parseInt(edtMinSalary.getText().toString())<1000000){
//            edtMinSalary.setError("Lương không hợp lệ");
//           // valid=true;
//        }
//        if(Integer.parseInt(edtMaxSalary.getText().toString())<1000000){
//            edtMaxSalary.setError("Lương không hợp lệ");
//            //valid=true;
//        }
//        if(Integer.parseInt(edtMinSalary.getText().toString())>=Integer.parseInt(edtMaxSalary.getText().toString())){
//            edtMaxSalary.setError("Lương phải lớn hơn");
//           // valid=true;
//        }
//        if(edtJobDecription.getText().toString().length()<=0) {
//            edtJobDecription.setError("Chưa nhập mô tả công việc");
//            //valid = true;
//        }

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
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

    public void Save() {
        dataPostJob = new DataPostJob();

        dataPostJob.setNameCompany(edtCompanyTitle.getText().toString());
        dataPostJob.setNameJob(edtJobTitle.getText().toString());
        dataPostJob.setTypeJob(Type.get(spnJobType.getSelectedItemPosition()));
        dataPostJob.setMaxSalary(edtMaxSalary.getText().toString());
        dataPostJob.setMinSalary(edtMinSalary.getText().toString());
        dataPostJob.setEach(Each.get(spnEach.getSelectedItemPosition()));
        dataPostJob.setNumberEmployer(NumHires.get(spnNumHires.getSelectedItemPosition()));
        dataPostJob.setDescription(edtJobDecription.getText().toString());
        dataPostJob.setQualification(edtQualification.getText().toString());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yy");
        String date = df.format(c.getTime());
        dataPostJob.setTime(date);
        presenter = new PresenterPostJob(this);
        Log.e("kt",FirebaseAuth.getInstance().getUid() + "");
        presenter.SavePost(FirebaseAuth.getInstance().getUid(),dataPostJob);
        Log.e("kt","ad");


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void SavePost(String Uid, DataPostJob dataPostJob) {

    }
}
