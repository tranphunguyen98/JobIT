package com.example.team32gb.jobit.View.PostJob;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Toast;

import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Presenter.PostJob.PresenterInPostJob;
import com.example.team32gb.jobit.Presenter.PostJob.PresenterPostJob;
import com.example.team32gb.jobit.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
    private EditText edtJobDescription;
    private EditText edtQualification;
    private Button btnPost;

    boolean valid = false;

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
        edtJobDescription =findViewById(R.id.edtJobDescription);
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


        edtCompanyTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtCompanyTitle.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtCompanyTitle.setError("Chưa nhập tên công ty");
                }
                else
                    valid = true;
            }
        });
        edtJobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtJobTitle.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtJobTitle.setError("Chưa nhập tên công việc");
                }
                else
                    valid = true;
            }
        });

        edtMinSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (edtMinSalary.getText().toString().length() <= 0) {

                        valid=false;
                        edtMinSalary.setError("Lương không hợp lệ");
                        Log.e("KT","1");

                    }
                    else if(Integer.parseInt(edtMinSalary.getText().toString()) < 10000){
                        valid=false;
                        edtMinSalary.setError("Lương phải lớn hơn 10000 VND");
                    }
                    else
                        valid = true;
            }
        });

        edtMaxSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtMaxSalary.getText().toString().length() <= 0) {

                    valid=false;
                    edtMaxSalary.setError("Lương không hợp lệ");
                    Log.e("KT","1");

                }
                else  if(Integer.parseInt(edtMaxSalary.getText().toString()) < 10000){
                    valid=false;
                    edtMaxSalary.setError("Lương lớn hơn 10000 VND");
                }
            }
        });

        edtJobDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtJobDescription.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtJobDescription.setError("Chưa nhập mô tả công việc");
                }
                else
                    valid = true;
            }
        });


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtCompanyTitle.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtCompanyTitle.setError("Chưa nhập tên công việc");
                }
                if(edtJobTitle.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtJobTitle.setError("Chưa nhập tên công việc");
                }
                if (edtMinSalary.getText().toString().length() <= 0) {

                    valid=false;
                    edtMinSalary.setError("Lương không hợp lệ");
                }
                else  if(Integer.parseInt(edtMinSalary.getText().toString()) < 10000){
                    valid=false;
                    edtMinSalary.setError("Lương phải lớn hơn 10000 VND");
                }
                if (edtMaxSalary.getText().toString().length() <= 0) {

                    valid=false;
                    edtMaxSalary.setError("Lương không hợp lệ");
                }
                else  if(Integer.parseInt(edtMaxSalary.getText().toString()) < 10000){
                    valid=false;
                    edtMaxSalary.setError("Lương phải lớn hơn 10000 VND");
                }
                if (edtMinSalary.getText().toString().length() > 0 && edtMaxSalary.getText().toString().length() > 0){
                    if (Integer.parseInt(edtMinSalary.getText().toString()) >= Integer.parseInt(edtMaxSalary.getText().toString())) {
                        edtMaxSalary.setError("Lương phải lớn hơn");
                        valid = false;
                    }
                    else
                        valid = true;
                }
                if (edtJobDescription.getText().toString().length() <= 0) {
                    edtJobDescription.setError("Chưa nhập mô tả công việc");
                    valid = false;
                }
                if(valid)
                {
                    Save();
                    Toast.makeText(getApplication(), "Đăng kí thành công",Toast.LENGTH_LONG).show();
                    edtCompanyTitle.setText("");
                    edtCompanyTitle.setError(null);
                    edtJobTitle.setText("");
                    edtJobTitle.setError(null);
                    edtMinSalary.setText("");
                    edtMinSalary.setError(null);
                    edtMaxSalary.setText("");
                    edtMaxSalary.setError(null);
                    edtJobDescription.setText("");
                    edtJobDescription.setError(null);
                    edtQualification.setText("");
                }
                else
                    Toast.makeText(getApplication(), "Đăng kí không thành thành công",Toast.LENGTH_LONG).show();

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
        dataPostJob.setDescription(edtJobDescription.getText().toString());
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
}
