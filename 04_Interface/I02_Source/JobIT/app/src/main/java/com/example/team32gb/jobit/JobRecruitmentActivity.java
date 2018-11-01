package com.example.team32gb.jobit;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class JobRecruitmentActivity extends AppCompatActivity {
  private Toolbar myToolBar;
    private ActionBar actionBar;
    private FrameLayout mainFrag;
    private BottomNavigationView mainNav;

    private EditText edtJobTitle;
    private Spinner spnJobType;
    private EditText edtMinSalary;
    private EditText edtMaxSalary;
    private Spinner spnEach;
    private Spinner spnNumHires;
    private EditText edtJobDecription;
    private EditText edtQualification;
    private Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_recruitment);

        mainFrag = findViewById(R.id.mainFrag);
        mainNav = findViewById(R.id.mainNav);

        myToolBar = findViewById(R.id.tbJobRecruitment);
        myToolBar.setTitle("Danh sách việc làm ");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

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

        setFragment(new PostedFragment());

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navPosted:
                        setFragment(new PostedFragment());
                        return true;
                    case R.id.navWaiting:
                        setFragment(new WaitingAcceptFragment());
                        return true;
                    case R.id.navExpire:
                        setFragment(new ExpireFragment());
                        return true;
                    case R.id.navUploadPost:
                        setFragment(new UpLoadPostFragment());
                        return true;
                        default:
                            return false;
                }
            }
        });
        //xử lys spinner loại công việc
        final ArrayList<String> Type = new ArrayList<String>();
        Type.add("bán thời gian");
        Type.add("toàn thời gian");
        Type.add("hợp đồng");
        Type.add("thực tập");
        Type.add("tạm thời");

        ArrayAdapter<String> adapterType=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Type);
        adapterType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnJobType.setAdapter(adapterType);


        //Xử lý Spinner "Mỗi"
        final ArrayList<String>  Each = new ArrayList<String>();
        Each.add("giờ");
        Each.add("ngày");
        Each.add("tuần");
        Each.add("tháng");
        Each.add("năm");

        ArrayAdapter<String> adapterEach=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Each);
        adapterEach.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnEach.setAdapter(adapterEach);

        //Xử lý Spinner số lượng người cần tuyển
        final ArrayList<String> NumHires = new ArrayList<String>();
        NumHires.add("1 người");
        NumHires.add("2 đến 5 người");
        NumHires.add("5 đến 10 người");
        NumHires.add("Hơn 10 người");
        NumHires.add("Nhu cầu liên tục");

        ArrayAdapter<String> adapterNumHires= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,NumHires);
        adapterNumHires.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNumHires.setAdapter(adapterNumHires);


        //Xử lý Button Đăng
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                boolean valid=false;
                if(id==R.id.btnPost){
                    if(edtJobTitle.getText().toString().length()<=0){
                        edtJobTitle.setError("Chưa nhập tên công việc");
                        valid=true;
                    }
                    if(Integer.parseInt(edtMinSalary.getText().toString())<1000000){
                        edtMinSalary.setError("Lương không hợp lệ");
                        valid=true;
                    }
                    if(Integer.parseInt(edtMaxSalary.getText().toString())<1000000){
                        edtMaxSalary.setError("Lương không hợp lệ");
                        valid=true;
                    }
                    if(Integer.parseInt(edtMinSalary.getText().toString())>=Integer.parseInt(edtMaxSalary.getText().toString())){
                        edtMaxSalary.setError("Lương phải lớn hơn");
                    }
                    if(edtJobDecription.getText().toString().length()<=0){
                        edtJobDecription.setError("Chưa nhập mô tả công việc");
                    }


                }
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
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrag,fragment);
        fragmentTransaction.commit();
    }
}
