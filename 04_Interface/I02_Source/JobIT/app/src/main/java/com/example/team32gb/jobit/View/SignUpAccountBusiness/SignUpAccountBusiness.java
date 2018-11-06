package com.example.team32gb.jobit.View.SignUpAccountBusiness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private boolean valid = false;
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

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtName.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtName.setError("Chưa nhập tên công ty");
                }
                else
                    valid = true;
            }
        });
        edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtAddress.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtAddress.setError("Chưa nhập địa chỉ công ty");
                }
                else
                    valid = true;
            }
        });
        edtIntroduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtIntroduce.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtIntroduce.setError("Chưa nhập mô tả công ty");
                }
                else
                    valid = true;
            }
        });
        edtNamePresenter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtNamePresenter.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtNamePresenter.setError("Chưa nhập tên người liên hệ");
                }
                else
                    valid = true;
            }
        });
        edtPhonePresenter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtPhonePresenter.getText().toString().length() <= 0)
                {
                    valid = false;
                    edtPhonePresenter.setError("Chưa nhập số điện thoại của người liên hệ");
                }
                else
                    valid = true;
            }
        });

        btnSignUp.setOnClickListener(this);

        presenter = new PresenterLogicSignUpAccountBusiness(this);
    }

    @Override
    public void onClick(View v) {
        if(edtName.getText().toString().length() <= 0)
        {
            valid = false;
            edtName.setError("Chưa nhập tên công ty");
        }
        if(edtAddress.getText().toString().length() <= 0)
        {
            valid = false;
            edtAddress.setError("Chưa nhập địa chỉ công ty");
        }
        if(edtIntroduce.getText().toString().length() <= 0)
        {
            valid = false;
            edtIntroduce.setError("Chưa nhập mô tả công ty");
        }
        if(edtNamePresenter.getText().toString().length() <= 0)
        {
            valid = false;
            edtNamePresenter.setError("Chưa nhập tên người liên hệ");
        }
        if(edtPhonePresenter.getText().toString().length() <= 0)
        {
            valid = false;
            edtPhonePresenter.setError("Chưa nhập số điện thoại của người liên hệ");
        }
        if(valid){
            Save();
            Toast.makeText(getApplication(), "Đăng kí thành công",Toast.LENGTH_LONG).show();
            edtName.setText(" ");
            edtAddress.setText(" ");
            edtIntroduce.setText(" ");
            edtNamePresenter.setText(" ");
            edtPhonePresenter.setText(" ");
            Util.jumpActivity(this,HomeRecruitmentActivity.class);
        }
        else{
            Toast.makeText(getApplication(), "Đăng kí không thành thành công",Toast.LENGTH_LONG).show();
        }
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
    public void Save(){
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

    }
}
