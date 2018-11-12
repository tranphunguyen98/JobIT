package com.example.team32gb.jobit.Model.AdminApproval;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.team32gb.jobit.Utility.Config.APPROVAL_FAIL;
import static com.example.team32gb.jobit.Utility.Config.APPROVAL_SUCCESSFUL;
import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY;
import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class ModelApprovalCompany {
    DatabaseReference dbrCompanyWatingApproval;
    InfoCompanyModel model= new InfoCompanyModel();
    String tag = "Admin Approval";

    public ModelApprovalCompany() {
        dbrCompanyWatingApproval = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY_WAITING_APPROVAL);
    }


    public boolean onApproval(final CompanyWaitingApprovalModel company, boolean isApproval) {
        boolean result = true;

        if (dbrCompanyWatingApproval.child(company.idCompany) != null) {
            DatabaseReference dbrCompany = FirebaseDatabase.getInstance().getReference()
                    .child(REF_INFO_COMPANY).child(company.getIdCompany()).child("approvalMode");

            if (isApproval){
                dbrCompany.setValue(APPROVAL_SUCCESSFUL);
            }
            else {
                dbrCompany.setValue(APPROVAL_FAIL);
            }

            dbrCompanyWatingApproval.child(company.getIdCompany()).removeValue().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(tag, "delete on server fail!");
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e(tag, "delete on server successful!");

                }
            });

        }

        else{
            result =false;
        }
        //todo gửi thông báo FCM về cho nhà tuyển dụng

        return result;
    }

}
