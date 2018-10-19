package com.example.team32gb.demomvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewXuLyDangNhap {
    private EditText edtName, edtPassword;
    private Button btnDangNhap;
//    private PresenterImpXuLyDangNhap presenterImpXuLyDangNhap;
    private PresenterLogicXuLyDangNhap presenterLogicXuLyDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        presenterLogicXuLyDangNhap = new PresenterLogicXuLyDangNhap(this);
        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        presenterLogicXuLyDangNhap.kiemTraDangNhap("TPN","123");
    }

    @Override
    public void DangNhapThanhCong() {
        Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangNhapThanhCong(String str) {
        Toast.makeText(this, "Thanh Cong" + str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangNhapThatBai() {
            Toast.makeText(this, "That bai", Toast.LENGTH_SHORT).show();
    }
}
