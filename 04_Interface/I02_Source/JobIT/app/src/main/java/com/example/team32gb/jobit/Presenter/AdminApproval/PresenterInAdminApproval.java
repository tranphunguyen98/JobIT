package com.example.team32gb.jobit.Presenter.AdminApproval;

import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;


interface PresenterInAdminApproval {
    void onCreate();
    void onDestroy();
    boolean onApproval(CompanyWaitingApprovalModel company, boolean isApproval);
}
