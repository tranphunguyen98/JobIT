package com.example.team32gb.jobit.Model.AdminApproval;

//import com.google.firebase.database.DatabaseReference;

/*Model của company đang chờ nhà tuyển dụng phê duyệt*/
public class CompanyWaitingApprovalModel {
    String idCompany;
    String dateSendApproval;

    public CompanyWaitingApprovalModel() {
    }

    public CompanyWaitingApprovalModel(String idCompany, String dateSendApproval) {
        this.idCompany = idCompany;
        this.dateSendApproval = dateSendApproval;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getDateSendApproval() {
        return dateSendApproval;
    }

    public void setDateSendApproval(String dateSendApproval) {
        this.dateSendApproval = dateSendApproval;
    }
}


