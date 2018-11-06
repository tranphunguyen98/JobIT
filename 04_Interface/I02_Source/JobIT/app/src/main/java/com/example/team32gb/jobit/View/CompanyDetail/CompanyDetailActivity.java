package com.example.team32gb.jobit.View.CompanyDetail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanyDetailActivity extends AppCompatActivity {
    private TextView txtNameCompany,txtSizeCompany,txtTypeCompany,txtAddress,txtIntroduce,txtContact,txtPhone;
    private Button btnCompanyAvatar;
    String idCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        btnCompanyAvatar = findViewById(R.id.imgAvatarCompanyDetail);
        txtNameCompany = this.findViewById(R.id.txtNameCompany);
        txtAddress = this.findViewById(R.id.txtAddressCompany);
        txtContact = this.findViewById(R.id.txtContact);
        txtPhone = this.findViewById(R.id.txtPhone);
        txtSizeCompany = this.findViewById(R.id.txtSizeCompanyDetail);
        txtTypeCompany = this.findViewById(R.id.txtTypeCompanyDetail);

        txtIntroduce = this.findViewById(R.id.txtIntroduceCompany);

        Intent intent = getIntent();

       
        idCompany = intent.getStringExtra("idCompany");
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("companys").child(idCompany);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InfoCompanyModel model = dataSnapshot.getValue(InfoCompanyModel.class);
                if(model != null) {
                    txtNameCompany.setText(model.getName());
                    txtAddress.setText("Địa chỉ: " + model.getAddress());
                    txtContact.setText("Liên hệ: " + model.getNamePresenter());
                    txtPhone.setText("Số điện thoại: " + model.getPhoneNumberPresenter());
                    txtSizeCompany.setText("Quy mô công ty: " + model.getSize());
                    txtTypeCompany.setText("Kiểu công ty: " + model.getType());
                    txtIntroduce.setText(model.getIntroduce());
                } else {
                    Toast.makeText(CompanyDetailActivity.this, "Lỗi khi lấy thông tin", Toast.LENGTH_SHORT).show();
                }
               
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
