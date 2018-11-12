package com.example.team32gb.jobit.Model.SignUpAccountBusiness;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.Utility.Config;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class ModelSignUpAccountBusiness {
    private InfoCompanyModel companyModel;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private GreenRobotEventBus eventBus;

    public ModelSignUpAccountBusiness() {
        companyModel = new InfoCompanyModel();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(Config.REF_INFO_COMPANY);
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void getInfoCompanyFromUid(String uid){
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    companyModel = dataSnapshot.getValue(InfoCompanyModel.class);
                    eventBus.post(companyModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void saveInfoComanyToServer(String uid,InfoCompanyModel companyModel) {
        Log.e("kiemtra1",uid + companyModel.getName());

        //Lấy thời gian nộp hồ sơ cần duyệt
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String dateSendApproval = df.format(date);

        databaseReference.child(uid).setValue(companyModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("kietra1","them vao companys");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        CompanyWaitingApprovalModel newCompany = new CompanyWaitingApprovalModel(uid, dateSendApproval);
        DatabaseReference refData = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY_WAITING_APPROVAL);
        refData.child(uid).setValue(newCompany).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("kietra1",e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Log.e("kietra1",aVoid.toString() + "thanh cong");

            }
        });
    }
}
