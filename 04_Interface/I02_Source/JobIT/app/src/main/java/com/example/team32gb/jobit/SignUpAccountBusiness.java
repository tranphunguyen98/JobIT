package com.example.team32gb.jobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignUpAccountBusiness extends AppCompatActivity implements View.OnClickListener {
    private Spinner spSize, spType,spProvince;
    private Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_account_business);

        spSize = findViewById(R.id.spSize);
        spType = findViewById(R.id.spType);
        spProvince = findViewById(R.id.spProvince);
        btnSignUp = findViewById(R.id.btnSignUp);

        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,R.array.QuyMoCongTy,R.layout.support_simple_spinner_dropdown_item);
        spSize.setAdapter(adapterSize);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.DanhSachLoaiHinhCongTy,R.layout.support_simple_spinner_dropdown_item);
        spType.setAdapter(adapterType);

        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,R.array.TinhThanh,R.layout.support_simple_spinner_dropdown_item);
        spProvince.setAdapter(adapterProvince);

        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HomeRecruitmentActivity.class);
        startActivity(intent);
    }
}
