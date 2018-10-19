package com.example.team32gb.jobit.View.MyJob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.team32gb.jobit.R;

public class MyJobActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSavedJob;
    Button btnAppliedJob;
    Button btnInterviewJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job);
        btnSavedJob = this.findViewById(R.id.btnSavedJob);
        btnAppliedJob = this.findViewById(R.id.btnAppliedJob);
        btnInterviewJob = this.findViewById(R.id.btnInterviewJob);

        btnSavedJob.setOnClickListener(this);
        btnAppliedJob.setOnClickListener(this);
        btnInterviewJob.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSavedJob:
                //TODO
                break;
            case R.id.btnAppliedJob:
                //TODO
                break;
            case R.id.btnInterviewJob:
                //TODO
                break;
            default:
                break;
        }

    }
}
