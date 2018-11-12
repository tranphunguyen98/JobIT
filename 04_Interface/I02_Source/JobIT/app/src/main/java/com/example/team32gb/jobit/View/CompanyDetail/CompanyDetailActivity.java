package com.example.team32gb.jobit.View.CompanyDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.SignUpAccountBusiness.SignUpAccountBusiness;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanyDetailActivity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;

    private TextView txtNameCompany, txtSizeCompany, txtTypeCompany, txtAddress, txtIntroduce, txtContact, txtPhone;
    private Button btnEdit;
    private AppCompatImageButton imageButton;
    private LinearLayout lnEdit;
    private ProgressDialog progressDialog;
    String idCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        myToolBar = findViewById(R.id.tbDetailCompany);

        txtNameCompany = this.findViewById(R.id.txtNameCompany);
        txtAddress = this.findViewById(R.id.txtAddressCompany);
        txtContact = this.findViewById(R.id.txtContact);
        txtPhone = this.findViewById(R.id.txtPhone);
        txtSizeCompany = this.findViewById(R.id.txtSizeCompanyDetail);
        txtTypeCompany = this.findViewById(R.id.txtTypeCompanyDetail);

        imageButton = findViewById(R.id.imgAvatarCompanyDetail);

        lnEdit = this.findViewById(R.id.lnEdit);
        btnEdit = this.findViewById(R.id.btnEditJob);

        txtIntroduce = this.findViewById(R.id.txtIntroduceCompany);

        myToolBar.setTitle("Thông tin công ty");
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        idCompany = intent.getStringExtra("idCompany");

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        if(sharedPreferences.getInt(Config.USER_TYPE,0) == Config.IS_RECRUITER) {
            idCompany = FirebaseAuth.getInstance().getUid();
            lnEdit.setVisibility(View.VISIBLE);
        }

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("companys").child(idCompany);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final InfoCompanyModel model = dataSnapshot.getValue(InfoCompanyModel.class);
                if (model != null) {
                    txtNameCompany.setText(model.getName());
                    txtAddress.setText("Địa chỉ: " + model.getAddress());
                    txtContact.setText("Liên hệ: " + model.getNamePresenter());
                    txtPhone.setText("Số điện thoại: " + model.getPhoneNumberPresenter());
                    txtSizeCompany.setText("Quy mô công ty: " + model.getSize());
                    txtTypeCompany.setText("Kiểu công ty: " + model.getType());
                    txtIntroduce.setText(model.getIntroduce());

                    btnEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(CompanyDetailActivity.this,SignUpAccountBusiness.class);
                            intent1.putExtra("bundle",model);
                            startActivity(intent1);
                        }
                    });
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(CompanyDetailActivity.this, "Lỗi khi lấy thông tin", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listjob_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
