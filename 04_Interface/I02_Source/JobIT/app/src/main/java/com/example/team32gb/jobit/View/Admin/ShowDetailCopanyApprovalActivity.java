package com.example.team32gb.jobit.View.Admin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.Presenter.AdminApproval.PresenterAdminApproval;
import com.example.team32gb.jobit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY;
import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class ShowDetailCopanyApprovalActivity extends AppCompatActivity {
    TextView txtName;
    TextView txtDate;
    TextView txtType;
    TextView txtSize;
    TextView txtAddress;
    TextView txtProvince;
    TextView txtIntroduce;
    TextView txtNamePresenter;
    TextView txtPhoneNumberPresenter;
    Button btnApprovalGreen;
    Button brnApprovalCancelRed;

    Context context;
    Dialog dialog;
    Button btnApproval;
    Button btnApprovalCancel;
    TextView txtAdminAskBeforeApproval;

    PresenterAdminApproval presenter;
    DatabaseReference refData;
    InfoCompanyModel model = new InfoCompanyModel();
    CompanyWaitingApprovalModel modelCompanyWaiting ;
    String idCompany;
    String dateSendApproval;
    boolean isApproval = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_copany_approval);
        context = getApplicationContext();

        txtName = findViewById(R.id.txtNameCompanyApproval);
        txtDate = findViewById(R.id.txtDateSendCompanyApproval);
        txtType = findViewById(R.id.txtTypeCompanyApproval);
        txtSize = findViewById(R.id.txtSizeCompanyApproval);
        txtAddress = findViewById(R.id.txtAddressCompanyApproval);
        txtProvince = findViewById(R.id.txtProvinceCompanyApproval);
        txtIntroduce = findViewById(R.id.txtIntroduceCompanyApproval);
        txtNamePresenter = findViewById(R.id.txtNamePresentCompanyApproval);
        txtPhoneNumberPresenter = findViewById(R.id.txtPhoneCompanyApproval);
        btnApprovalGreen = findViewById(R.id.btnDetailCompanyApprovalOK);
        brnApprovalCancelRed = findViewById(R.id.btnDetailCompanyApprovalCancel);

        Intent intent = getIntent();
        idCompany = intent.getStringExtra(AdminApprovalActivity.ID_COMPANY);
        dateSendApproval = intent.getStringExtra(AdminApprovalActivity.DATE_SEND_APPROVAL);
        modelCompanyWaiting = new CompanyWaitingApprovalModel(idCompany, dateSendApproval);

        refData = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY).child(idCompany);
        if (refData != null) {
            refData.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    model = dataSnapshot.getValue(InfoCompanyModel.class);
                    Log.e("admin", "get model company");
                    txtName.setText(model.getName());
                    Log.e("admin", "Set text name company");
                    txtDate.setText(dateSendApproval);
                    txtType.setText(model.getType());
                    txtSize.setText(model.getSize());
                    txtAddress.setText(model.getAddress());
                    txtProvince.setText(model.getProvince());
                    txtIntroduce.setText(model.getIntroduce());
                    txtNamePresenter.setText(model.getNamePresenter());
                    txtPhoneNumberPresenter.setText(model.getPhoneNumberPresenter());

                    btnApprovalGreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("admin", "test btn");
                            isApproval = true;

                            setUpDialog();
                            dialog.show();
                        }
                    });
                    brnApprovalCancelRed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("admin", "test btn");
                            isApproval = false;

                            setUpDialog();
                            dialog.show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    void setUpDialog() {
        presenter =  new PresenterAdminApproval();
        dialog = new Dialog(ShowDetailCopanyApprovalActivity.this);

        View v = LayoutInflater.from(context).inflate(R.layout.admin_approval_dialog, null);
        btnApproval = v.findViewById(R.id.btnAdminApprovalOKDialog);
        btnApprovalCancel = v.findViewById(R.id.btnAdminApprovalCancelDialog);
        txtAdminAskBeforeApproval = v.findViewById(R.id.txtAdminAskBeforeApproval);


        if (isApproval) {
            txtAdminAskBeforeApproval.setText("Bạn có chắc chắn muốn duyệt hồ sơ này?");
        } else {
            txtAdminAskBeforeApproval.setText("Bạn có chắc chắn hủy hồ sơ này?");
        }

        btnApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isApproval) {
                    if (presenter.onApproval(modelCompanyWaiting, isApproval)) {
                        dialog.dismiss();
                        Toast.makeText(ShowDetailCopanyApprovalActivity.this, "Duyệt thành công", Toast.LENGTH_SHORT).show();
                        closeActivity();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(ShowDetailCopanyApprovalActivity.this, "Duyệt thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {  //Không duyệt
                    if (presenter.onApproval(modelCompanyWaiting, isApproval)) {
                        dialog.dismiss();
                        Toast.makeText(ShowDetailCopanyApprovalActivity.this, "Hủy hồ sơ thành công", Toast.LENGTH_SHORT).show();
                        closeActivity();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(ShowDetailCopanyApprovalActivity.this, "Hủy hồ sơ thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnApprovalCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(v);
    }

    void closeActivity(){
        Intent intent = new Intent(ShowDetailCopanyApprovalActivity.this, AdminApprovalActivity.class);
        startActivity(intent);
        finish();
    }
}
