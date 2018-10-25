package com.example.nguyen.jobrecruitment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class JobRecruitment extends AppCompatActivity {
private EditText edtJobTitle;
private EditText edtMinSalary;
private EditText edtMaxSalary;
private Spinner spnEach;
private Spinner spnNumHires;
private EditText edtJobDecription;
private EditText edtQualification;
private Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_recruitment);

        edtJobTitle=findViewById(R.id.edtJobTittle);
        edtMinSalary=findViewById(R.id.edtMinSalary);
        edtMaxSalary=findViewById(R.id.edtMaxSalary);
        spnEach=findViewById(R.id.spnEach);
        spnNumHires=findViewById(R.id.spnNumHires);
        edtJobDecription=findViewById(R.id.edtJobDescription);
        edtQualification=findViewById(R.id.edtQualification);
        btnPost=findViewById(R.id.btnPost);


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
}
