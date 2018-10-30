package com.example.team32gb.jobit.Presenter.SignUpAccountBusiness;

import android.icu.text.IDNA;
import android.view.Display;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.Model.SignUpAccountBusiness.ModelSignUpAccountBusiness;

public interface PresenterInSignUpAccountBusiness {
    void onCreate();
    void onDestroy();
    void saveInfoCompany(String uid,InfoCompanyModel infoCompanyModel);
    void getInfoCompany(String uid);
    void showInfoCompany(InfoCompanyModel companyModel);
}
