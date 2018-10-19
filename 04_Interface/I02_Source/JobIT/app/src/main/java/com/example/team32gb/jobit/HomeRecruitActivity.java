package com.example.team32gb.jobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HomeRecruitActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnUpLoad,btnPosted,btnCandidateList,btnFileOfRecruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_recruit);

        btnUpLoad = findViewById(R.id.btnUploadPost);
        btnPosted = findViewById(R.id.btnPosted);
        btnCandidateList = findViewById(R.id.btnCandidateList);
        btnFileOfRecruit = findViewById(R.id.btnFileOfRecruit);

        btnUpLoad.setOnClickListener(this);
        btnPosted.setOnClickListener(this);
        btnCandidateList.setOnClickListener(this);
        btnFileOfRecruit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnUploadPost:
                // class đăng tin do nguyên làm cái này để vậy thôi nhan
                 intent = new Intent(this,PostedActivity.class);
              //  startActivity(intent);
                break;
            case R.id.btnPosted:
                 intent = new Intent(this,PostedActivity.class);
               // startActivity(intent);
                break;
            case R.id.btnCandidateList:
                 intent = new Intent(this,ListCandidateAppliedActivity.class);
               // startActivity(intent);
                break;
            case R.id.btnFileOfRecruit:
                 intent = new Intent(this,PostedActivity.class);
                break;
            default:
                return;

        }
        startActivity(intent);
    }
}
