package com.example.team32gb.jobit.Model.Report;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_RECRUITERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT_WAITING_ADMIN_APPROVAL;

public class ReportWaitingAdminApprovalModel {
    String idReport;
    String idAccused; //id người bị tố cáo
    String dateSendReport;

    public ReportWaitingAdminApprovalModel() {
    }

    public ReportWaitingAdminApprovalModel(String idReport, String idAccused, String dateSendReport) {
        this.idReport = idReport;
        this.idAccused = idAccused;
        this.dateSendReport = dateSendReport;
    }

    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
    }

    public String getIdAccused() {
        return idAccused;
    }

    public void setIdAccused(String idAccused) {
        this.idAccused = idAccused;
    }

    public String getDateSendReport() {
        return dateSendReport;
    }

    public void setDateSendReport(String dateSendReport) {
        this.dateSendReport = dateSendReport;
    }

    public void onSendWarningReportToJobseeker(String messageFromAdmin) {
        DatabaseReference refData = FirebaseDatabase.getInstance().getReference()
                .child(REF_REPORT).child(REF_JOBSEEKERS_NODE)
                .child(idAccused).child(idReport).child("adminComment");
        //Đưa comment của admin lên firebase dùng để kiểm tra và gửi notification về cho người nhận cảnh cáo
        refData.setValue(messageFromAdmin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference refWaitingReport = FirebaseDatabase.getInstance().getReference()
                        .child(REF_REPORT_WAITING_ADMIN_APPROVAL).child(REF_JOBSEEKERS_NODE)
                        .child(idReport);
                //Xóa tố cáo trong mục chờ duyệt
                refWaitingReport.removeValue();
            }
        });

    }

    public void onIgnoreReportJobseeker() {
        DatabaseReference refWaitingReport = FirebaseDatabase.getInstance().getReference()
                .child(REF_REPORT_WAITING_ADMIN_APPROVAL).child(REF_JOBSEEKERS_NODE)
                .child(idReport);
        //Xóa tố cáo trong mục chờ duyệt
        refWaitingReport.removeValue();
    }

    public void onSendWarningReportToRecuiter(String messageFromAdmin){
        DatabaseReference refData = FirebaseDatabase.getInstance().getReference()
                .child(REF_REPORT).child(REF_RECRUITERS_NODE)
                .child(idAccused).child(idReport).child("adminComment");
        //Đưa comment của admin lên firebase dùng để kiểm tra và gửi notification về cho người nhận cảnh cáo
        refData.setValue(messageFromAdmin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference refWaitingReport = FirebaseDatabase.getInstance().getReference()
                        .child(REF_REPORT_WAITING_ADMIN_APPROVAL).child(REF_RECRUITERS_NODE)
                        .child(idReport);
                //Xóa tố cáo trong mục chờ duyệt
                refWaitingReport.removeValue();
            }
        });
    }

    public void onIgnoreReportRecuiter() {
        DatabaseReference refWaitingReport = FirebaseDatabase.getInstance().getReference()
                .child(REF_REPORT_WAITING_ADMIN_APPROVAL).child(REF_RECRUITERS_NODE)
                .child(idReport);
        //Xóa tố cáo trong mục chờ duyệt
        refWaitingReport.removeValue();
    }
}
