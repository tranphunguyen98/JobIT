package com.example.team32gb.jobit.Presenter.AdminApproval;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.Model.AdminApproval.ModelApprovalCompany;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.team32gb.jobit.Utility.Config.REF_INFO_COMPANY_WAITING_APPROVAL;

public class PresenterAdminApprovalRecruiter implements PresenterInAdminApprovalRecruiter {
    private EventBus eventBus;
    DatabaseReference dbReference;
    List<CompanyWaitingApprovalModel> arrCompany;
    ModelApprovalCompany model ;

    public PresenterAdminApprovalRecruiter()
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
