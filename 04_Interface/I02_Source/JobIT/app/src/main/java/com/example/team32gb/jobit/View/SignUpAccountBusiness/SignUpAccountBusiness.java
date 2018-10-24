package com.example.team32gb.jobit.View.SignUpAccountBusiness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.Presenter.SignUpAccountBusiness.PresenterInSignUpAccountBusiness;
import com.example.team32gb.jobit.Presenter.SignUpAccountBusiness.PresenterLogicSignUpAccountBusiness;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Util;
import com.example.team32gb.jobit.View.HomeRecruitmentActivity.HomeRecruitmentActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpAccountBusiness extends AppCompatActivity implements View.OnClickListener,
                                            AdapterView.OnItemSelectedListener,ViewSignUpAccountBusiness {
    private Spinner spSize, spType,spProvince;
    private Button btnSignUp;
    private EditText edtName,edtAddress, edtIntroduce, edtNamePresenter, edtPhonePresenter;
    private PresenterInSignUpAccountBusiness presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_account_business);

        spSize = findViewById(R.id.spSize);
        spType = findViewById(R.id.spType);
        spProvince = findViewById(R.id.spProvince);
        btnSignUp = findViewById(R.id.btnSignUp);

        edtName = findViewById(R.id.edtNameCompanySignUp);
        edtAddress = findViewById(R.id.edtAddressCompany);
        edtIntroduce = findViewById(R.id.edtDescriptionCompany);
        edtNamePresenter = findViewById(R.id.edtContact);
        edtPhonePresenter = findViewById(R.id.edtPhonePresenter);


        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,R.array.QuyMoCongTy,R.layout.support_simple_spinner_dropdown_item);
        spSize.setAdapter(adapterSize);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.DanhSachLoaiHinhCongTy,R.layout.support_simple_spinner_dropdown_item);
        spType.setAdapter(adapterType);

        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,R.array.TinhThanh,R.layout.support_simple_spinner_dropdown_item);
        spProvince.setAdapter(adapterProvince);

        spSize.setOnItemSelectedListener(this);

        btnSignUp.setOnClickListener(this);

        presenter = new PresenterLogicSignUpAccountBusiness(this);
    }

    @Override
    public void onClick(View v) {
        InfoCompanyModel companyModel = new InfoCompanyModel();

        companyModel.setName(edtName.getText().toString());
        companyModel.setAddress(edtAddress.getText().toString());
        companyModel.setIntroduce(edtIntroduce.getText().toString());
        companyModel.setNamePresenter(edtNamePresenter.getText().toString());
        companyModel.setPhoneNumberPresenter(edtPhonePresenter.getText().toString());
        companyModel.setSize(spSize.getSelectedItem().toString());
        companyModel.setType(spType.getSelectedItem().toString());
        companyModel.setProvince(spProvince.getSelectedItem().toString());

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getUid();

        presenter.saveInfoCompany(uid,companyModel);
        Util.gotoActivity(this,HomeRecruitmentActivity.class);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showInfoCompany(InfoCompanyModel companyModel) {

    }
}
