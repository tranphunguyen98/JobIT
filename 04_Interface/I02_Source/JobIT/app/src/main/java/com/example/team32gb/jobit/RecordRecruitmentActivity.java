package com.example.team32gb.jobit;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import android.widget.Spinner;
import android.widget.TextView;

public class RecordRecruitmentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Toolbar myToolBar;
    private ActionBar actionBar;

    private TextView txtNameCompany,txtSizeCompany,txtTypeCompany,txtAddress,txtProvince,txtIntroduce,txtContact,txtPhone;
    private EditText edtNameCompany,edtAddress,edtIntroduce,edtPhone,edtContact;
    private Button btnSaveName,btnEditName;
    private Button btnSaveSize,btnEditSize;
    private Button btnSaveType,btnEditType;
    private Button btnSaveAddress,btnEditAddress;
    private Button btnSaveProvince,btnEditProvince;
    private Button btnSaveIntroduce,btnEditIntroduce;
    private Button btnSaveContact,btnEditContact;
    private Button btnSavePhone,btnEditPhone;

    private Spinner spSize;
    private Spinner spType;
    private Spinner spProvince;

   private String[] arrSize;
   private String[] arrType;
   private String[] arrProvince;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_recruitment_ml);

        myToolBar = findViewById(R.id.tbRecord);
        myToolBar.setTitle("Thông tin công ty");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtNameCompany = findViewById(R.id.txtNameCompany);
        edtNameCompany = findViewById(R.id.edtNameCompany);
        btnSaveName = findViewById(R.id.btnSaveName);
        btnEditName = findViewById(R.id.btnEditName);

        btnEditName.setOnClickListener(this);
        btnSaveName.setOnClickListener(this);

        txtSizeCompany = findViewById(R.id.txtSizeCompany);
        spSize = findViewById(R.id.spSize);
        btnSaveSize = findViewById(R.id.btnSaveSize);
        btnEditSize = findViewById(R.id.btnEditSize);

        btnEditSize.setOnClickListener(this);
        btnSaveSize.setOnClickListener(this);

        arrSize = getResources().getStringArray(R.array.QuyMoCongTy);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,R.array.QuyMoCongTy,R.layout.support_simple_spinner_dropdown_item);

        spSize.setAdapter(adapterSize);
        spSize.setOnItemSelectedListener(this);

        txtTypeCompany = findViewById(R.id.txtTypeCompany);
        spType = findViewById(R.id.spType);
        btnEditType = findViewById(R.id.btnEditType);
        btnSaveType = findViewById(R.id.btnSaveType);

        btnEditType.setOnClickListener(this);
        btnSaveType.setOnClickListener(this);

        arrType = getResources().getStringArray(R.array.DanhSachLoaiHinhCongTy);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,R.array.DanhSachLoaiHinhCongTy,R.layout.support_simple_spinner_dropdown_item);
        spType.setAdapter(adapterType);
        spType.setOnItemSelectedListener(this);

        txtAddress = findViewById(R.id.txtAddressCompany);
        edtAddress = findViewById(R.id.edtAddressCompany);
        btnEditAddress = findViewById(R.id.btnEditAddress);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);

        btnEditAddress.setOnClickListener(this);
        btnSaveAddress.setOnClickListener(this);

        txtProvince = findViewById(R.id.txtProvince);
        spProvince = findViewById(R.id.spProvince);
        btnEditProvince = findViewById(R.id.btnEditProvince);
        btnSaveProvince = findViewById(R.id.btnSaveProvince);

        btnEditProvince.setOnClickListener(this);
        btnSaveProvince.setOnClickListener(this);

        arrProvince = getResources().getStringArray(R.array.TinhThanh);
        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this,R.array.TinhThanh,R.layout.support_simple_spinner_dropdown_item);
        spProvince.setAdapter(adapterProvince);
        spProvince.setOnItemSelectedListener(this);

        txtIntroduce = findViewById(R.id.txtIntroduceCompany);
        edtIntroduce = findViewById(R.id.edtIntroduceCompany);
        btnSaveIntroduce = findViewById(R.id.btnSaveIntroduce);
        btnEditIntroduce = findViewById(R.id.btnEditIntroduce);

        btnEditIntroduce.setOnClickListener(this);
        btnSaveIntroduce.setOnClickListener(this);

        txtContact = findViewById(R.id.txtContact);
        edtContact= findViewById(R.id.edtContact);
        btnSaveContact = findViewById(R.id.btnSaveContact);
        btnEditContact = findViewById(R.id.btnEditContact);

        btnEditContact.setOnClickListener(this);
        btnSaveContact.setOnClickListener(this);

        txtPhone = findViewById(R.id.txtPhone);
        edtPhone= findViewById(R.id.edtPhone);
        btnSavePhone = findViewById(R.id.btnSavePhone);
        btnEditPhone = findViewById(R.id.btnEditPhone);

        btnEditPhone.setOnClickListener(this);
        btnSavePhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        long id = v.getId();
        if (id == R.id.btnEditName){
            btnSaveName.setVisibility(View.VISIBLE);
            txtNameCompany.setVisibility(View.GONE);
            edtNameCompany.setVisibility(View.VISIBLE);
        }
        if (id == R.id.btnSaveName){
           txtNameCompany.setText(edtNameCompany.getText());
            edtNameCompany.setText("");
            edtNameCompany.setVisibility(View.GONE);
            txtNameCompany.setVisibility(View.VISIBLE);
            btnSaveName.setVisibility(View.GONE);
        }
        if(id == R.id.btnEditSize){
            btnSaveSize.setVisibility(View.VISIBLE);
            txtSizeCompany.setVisibility(View.GONE);
            spSize.setVisibility(View.VISIBLE);
        }
        if(id == R.id.btnSaveSize){
            txtSizeCompany.setText(arrSize[spSize.getSelectedItemPosition()]);
            txtSizeCompany.setVisibility(View.VISIBLE);
            btnSaveSize.setVisibility(View.GONE);
            spSize.setVisibility(View.GONE);
        }
        if(id == R.id.btnEditType){
            btnSaveType.setVisibility(View.VISIBLE);
            txtTypeCompany.setVisibility(View.GONE);
            spType.setVisibility(View.VISIBLE);
        }
        if(id == R.id.btnSaveType){
            txtTypeCompany.setText(arrType[spType.getSelectedItemPosition()]);
            btnSaveType.setVisibility(View.GONE);
            txtTypeCompany.setVisibility(View.VISIBLE);
            spType.setVisibility(View.GONE);
        }
        if (id == R.id.btnEditAddress){
            btnSaveAddress.setVisibility(View.VISIBLE);
            txtAddress.setVisibility(View.GONE);
            edtAddress.setVisibility(View.VISIBLE);
        }
        if (id == R.id.btnSaveAddress){
            txtAddress.setText(edtAddress.getText());
            edtAddress.setText("");
            edtAddress.setVisibility(View.GONE);
            txtAddress.setVisibility(View.VISIBLE);
            btnSaveAddress.setVisibility(View.GONE);
        }
        if(id == R.id.btnEditProvince){
            btnSaveProvince.setVisibility(View.VISIBLE);
            txtProvince.setVisibility(View.GONE);
            spProvince.setVisibility(View.VISIBLE);
        }
        if(id == R.id.btnSaveProvince){
            txtProvince.setText(arrProvince[spProvince.getSelectedItemPosition()]);
            btnSaveProvince.setVisibility(View.GONE);
            txtProvince.setVisibility(View.VISIBLE);
            spProvince.setVisibility(View.GONE);
        }
        if (id == R.id.btnEditIntroduce){
            btnSaveIntroduce.setVisibility(View.VISIBLE);
            txtIntroduce.setVisibility(View.GONE);
            edtIntroduce.setVisibility(View.VISIBLE);
        }
        if (id == R.id.btnSaveIntroduce){
            txtIntroduce.setText(edtIntroduce.getText());
            edtIntroduce.setText("");
            edtIntroduce.setVisibility(View.GONE);
            txtIntroduce.setVisibility(View.VISIBLE);
            btnSaveIntroduce.setVisibility(View.GONE);
        }
        if (id == R.id.btnEditContact){
            btnSaveContact.setVisibility(View.VISIBLE);
            txtContact.setVisibility(View.GONE);
            edtContact.setVisibility(View.VISIBLE);
        }
        if (id == R.id.btnSaveContact){
            txtContact.setText(edtContact.getText());
            edtContact.setText("");
            edtContact.setVisibility(View.GONE);
            txtContact.setVisibility(View.VISIBLE);
            btnSaveContact.setVisibility(View.GONE);
        }
        if (id == R.id.btnEditPhone){
            btnSavePhone.setVisibility(View.VISIBLE);
            txtPhone.setVisibility(View.GONE);
            edtPhone.setVisibility(View.VISIBLE);
        }
        if (id == R.id.btnSavePhone){
            txtPhone.setText(edtPhone.getText());
            edtPhone.setText("");
            edtPhone.setVisibility(View.GONE);
            txtPhone.setVisibility(View.VISIBLE);
            btnSavePhone.setVisibility(View.GONE);
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
}
