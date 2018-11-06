package com.example.team32gb.jobit.View.SignUpAccountBusiness;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.Presenter.SignUpAccountBusiness.PresenterInSignUpAccountBusiness;
import com.example.team32gb.jobit.Presenter.SignUpAccountBusiness.PresenterLogicSignUpAccountBusiness;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.HomeRecruitmentActivity.HomeRecruitmentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecordRecruitmentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,ViewSignUpAccountBusiness {

    private Toolbar myToolBar;
    private ActionBar actionBar;

    private TextView txtNameCompany,txtSizeCompany,txtTypeCompany,txtAddress,txtProvince,txtIntroduce,txtContact,txtPhone;
    private EditText edtNameCompany,edtAddress,edtIntroduce,edtPhone,edtContact;

    private Button btnSave,btnEdit;

    private LinearLayout lnSize, lnType, lnProvice;

    private Spinner spSize;
    private Spinner spType;
    private Spinner spProvince;

   private String[] arrSize;
   private String[] arrType;
   private String[] arrProvince;

   private String name, address, size, type, province, phone, contact,introduce;

   private Boolean valid = false;

   private InfoCompanyModel infoCompanyModel = new InfoCompanyModel();

   private PresenterInSignUpAccountBusiness presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recruitment);

        myToolBar = findViewById(R.id.tbDetail);
        myToolBar.setTitle("Thông tin công ty");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtNameCompany = findViewById(R.id.txtNameCompany);
        edtNameCompany = findViewById(R.id.edtNameCompany);

        txtSizeCompany = findViewById(R.id.txtSizeCompany);
        spSize = findViewById(R.id.spSize);

        arrSize = getResources().getStringArray(R.array.QuyMoCongTy);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,R.array.QuyMoCongTy,R.layout.support_simple_spinner_dropdown_item);

        spSize.setAdapter(adapterSize);
        spSize.setOnItemSelectedListener(this);

        txtTypeCompany = findViewById(R.id.txtTypeCompany);
        spType = findViewById(R.id.spType);

        arrType = getResources().getStringArray(R.array.DanhSachLoaiHinhCongTy);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.DanhSachLoaiHinhCongTy,R.layout.support_simple_spinner_dropdown_item);
        spType.setAdapter(adapterType);
        spType.setOnItemSelectedListener(this);

        txtAddress = findViewById(R.id.txtAddressCompany);
        edtAddress = findViewById(R.id.edtAddressCompany);

        txtProvince = findViewById(R.id.txtProvince);
        spProvince = findViewById(R.id.spProvince);

        arrProvince = getResources().getStringArray(R.array.TinhThanh);
        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,R.array.TinhThanh,R.layout.support_simple_spinner_dropdown_item);
        spProvince.setAdapter(adapterProvince);
        spProvince.setOnItemSelectedListener(this);

        txtIntroduce = findViewById(R.id.txtIntroduceCompany);
        edtIntroduce = findViewById(R.id.edtIntroduceCompany);

        txtContact = findViewById(R.id.txtContact);
        edtContact= findViewById(R.id.edtContact);

        txtPhone = findViewById(R.id.txtPhone);
        edtPhone= findViewById(R.id.edtPhone);


        btnSave = findViewById(R.id.btnSave);
        btnEdit = findViewById(R.id.btnEdit);

        lnSize = findViewById(R.id.lnSize);
        lnType = findViewById(R.id.lnType);
        lnProvice = findViewById(R.id.lnProvice);

        name = txtNameCompany.getText().toString();
        address = txtAddress.getText().toString();
        size = txtSizeCompany.getText().toString();
        type = txtTypeCompany.getText().toString();
        introduce = txtIntroduce.getText().toString();
        province = txtProvince.getText().toString();
        contact = txtContact.getText().toString();
        phone = txtPhone.getText().toString();

        presenter = new PresenterLogicSignUpAccountBusiness(this);
        presenter.onCreate();
        presenter.getInfoCompany(FirebaseAuth.getInstance().getUid());

        btnEdit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        long id = v.getId();
        if (id == R.id.btnEdit){
            btnSave.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.GONE);
            txtNameCompany.setVisibility(View.GONE);
            edtNameCompany.setVisibility(View.VISIBLE);

            txtSizeCompany.setVisibility(View.GONE);
            spSize.setVisibility(View.VISIBLE);
            lnSize.setVisibility(View.VISIBLE);

            txtTypeCompany.setVisibility(View.GONE);
            spType.setVisibility(View.VISIBLE);
            lnType.setVisibility(View.VISIBLE);

            txtAddress.setVisibility(View.GONE);
            edtAddress.setVisibility(View.VISIBLE);

            txtProvince.setVisibility(View.GONE);
            spProvince.setVisibility(View.VISIBLE);
            lnProvice.setVisibility(View.VISIBLE);

            txtIntroduce.setVisibility(View.GONE);
            edtIntroduce.setVisibility(View.VISIBLE);

            txtContact.setVisibility(View.GONE);
            edtContact.setVisibility(View.VISIBLE);

            txtPhone.setVisibility(View.GONE);
            edtPhone.setVisibility(View.VISIBLE);

            edtNameCompany.setText(txtNameCompany.getText().toString());
            edtAddress.setText(txtAddress.getText().toString());
            edtIntroduce.setText(txtIntroduce.getText().toString());
            edtContact.setText(txtContact.getText().toString());
            edtPhone.setText(txtPhone.getText().toString());

            edtNameCompany.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(edtNameCompany.getText().toString().length() <= 0)
                    {
                        valid = false;
                        edtNameCompany.setError("Chưa nhập tên công ty");
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
            edtContact.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(edtContact.getText().toString().length() <= 0)
                    {
                        valid = false;
                        edtContact.setError("Chưa nhập tên người liên hệ");
                    }
                    else
                        valid = true;
                }
            });
            edtPhone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(edtPhone.getText().toString().length() <= 0)
                    {
                        valid = false;
                        edtPhone.setError("Chưa nhập số điện thoại của người liên hệ");
                    }
                    else
                        valid = true;
                }
            });

        }
        if (id == R.id.btnSave){



            if(edtNameCompany.getText().toString().length() <= 0)
            {
                valid = false;
                edtNameCompany.setError("Chưa nhập tên công ty");
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
            if(edtContact.getText().toString().length() <= 0)
            {
                valid = false;
                edtContact.setError("Chưa nhập tên người liên hệ");
            }
            if(edtPhone.getText().toString().length() <= 0)
            {
                valid = false;
                edtPhone.setError("Chưa nhập số điện thoại của người liên hệ");
            }

            if(valid){

                btnSave.setVisibility(View.GONE);
                btnEdit.setVisibility(View.VISIBLE);

                infoCompanyModel.setName(edtNameCompany.getText().toString());
                infoCompanyModel.setSize(arrSize[spSize.getSelectedItemPosition()]);
                infoCompanyModel.setType(arrType[spType.getSelectedItemPosition()]);
                infoCompanyModel.setAddress(edtAddress.getText().toString());
                infoCompanyModel.setProvince(arrProvince[spProvince.getSelectedItemPosition()]);
                infoCompanyModel.setIntroduce(edtIntroduce.getText().toString());
                infoCompanyModel.setNamePresenter(edtContact.getText().toString());
                infoCompanyModel.setPhoneNumberPresenter(edtPhone.getText().toString());

                txtNameCompany.setText(name + edtNameCompany.getText());
                edtNameCompany.setText(" ");
                edtNameCompany.setVisibility(View.GONE);
                txtNameCompany.setVisibility(View.VISIBLE);

                txtSizeCompany.setText(size + arrSize[spSize.getSelectedItemPosition()]);
                txtSizeCompany.setVisibility(View.VISIBLE);
                spSize.setVisibility(View.GONE);
                lnSize.setVisibility(View.GONE);

                txtTypeCompany.setText(type + arrType[spType.getSelectedItemPosition()]);
                txtTypeCompany.setVisibility(View.VISIBLE);
                spType.setVisibility(View.GONE);
                lnType.setVisibility(View.GONE);

                txtAddress.setText(address + edtAddress.getText());
                edtAddress.setText(" ");
                edtAddress.setVisibility(View.GONE);
                txtAddress.setVisibility(View.VISIBLE);

                txtProvince.setText(province + arrProvince[spProvince.getSelectedItemPosition()]);
                txtProvince.setVisibility(View.VISIBLE);
                spProvince.setVisibility(View.GONE);
                lnProvice.setVisibility(View.GONE);

                txtIntroduce.setText(introduce + edtIntroduce.getText());
                edtIntroduce.setText(" ");
                edtIntroduce.setVisibility(View.GONE);
                txtIntroduce.setVisibility(View.VISIBLE);

                txtContact.setText(contact + edtContact.getText());
                edtContact.setText(" ");
                edtContact.setVisibility(View.GONE);
                txtContact.setVisibility(View.VISIBLE);

                txtPhone.setText(phone + edtPhone.getText());
                edtPhone.setText(" ");
                edtPhone.setVisibility(View.GONE);
                txtPhone.setVisibility(View.VISIBLE);

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference dfData = databaseReference.child(Config.REF_INFO_COMPANY).child(FirebaseAuth.getInstance().getUid());
                dfData.setValue(infoCompanyModel).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplication(), "Sửa thông tin không thành thành công",Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplication(), "Sửa thông tin thành công",Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent(this, HomeRecruitmentActivity.class);
                finish();
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplication(), "Sửa hồ sơ không thành thành công",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

    @Override
    public void showInfoCompany(InfoCompanyModel companyModel) {
        Log.e("KTS",companyModel.getName() );
        Log.e("KT",txtNameCompany.getText().toString());
        txtNameCompany.setText(name + companyModel.getName());
        txtAddress.setText(address + companyModel.getAddress());
        txtSizeCompany.setText(size + companyModel.getSize());
        txtTypeCompany.setText(type + companyModel.getType());
        txtIntroduce.setText(introduce + companyModel.getIntroduce());
        txtProvince.setText(province + companyModel.getProvince());
        txtContact.setText(contact + companyModel.getNamePresenter());
        txtPhone.setText(phone + companyModel.getPhoneNumberPresenter());
    }
}
