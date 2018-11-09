package com.example.team32gb.jobit.Presenter.AdminApproval;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.Model.AdminApproval.ModelApprovalCompany;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class PresenterAdminApproval implements PresenterInAdminApproval {
    private EventBus eventBus;
    DatabaseReference dbReference;
    List<CompanyWaitingApprovalModel> arrCompany;
    ModelApprovalCompany model ;

    public PresenterAdminApproval()
    {
        arrCompany = new ArrayList<>();
        model = new ModelApprovalCompany();

        eventBus = new GreenRobotEventBus().getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child(REF_INFO_COMPANY_WAITING_APPROVAL);
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public boolean onApproval(CompanyWaitingApprovalModel company, boolean isApproval) {
        return model.onApproval(company, isApproval);
    }



}
