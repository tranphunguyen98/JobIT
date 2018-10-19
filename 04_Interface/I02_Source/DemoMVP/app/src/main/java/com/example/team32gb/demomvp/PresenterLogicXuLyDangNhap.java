package com.example.team32gb.demomvp;

import android.view.View;

public class PresenterLogicXuLyDangNhap  {

    ViewXuLyDangNhap viewXuLyDangNhap;
    public PresenterLogicXuLyDangNhap(ViewXuLyDangNhap viewXuLyDangNhap) {
        this.viewXuLyDangNhap =viewXuLyDangNhap;
    }

    public void kiemTraDangNhap(String name, String password) {
        if(name.equals("TPN") && password.equals("123")) {
            viewXuLyDangNhap.DangNhapThanhCong("Sring");
        }
        else viewXuLyDangNhap.DangNhapThatBai();
    }
    //Model

}
