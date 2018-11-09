package com.example.team32gb.jobit.View.Admin;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.AdminApproval.CompanyWaitingApprovalModel;
import com.example.team32gb.jobit.R;

public class AdminApprovalViewHolder extends RecyclerView.ViewHolder {
    TextView txtCompanyNameSendApproval;
    TextView txtDateSendApproval;
    ImageButton imgbtnUncheckApproval;
    ImageButton imgbtnCheckApproval;
    ImageButton imgbtnShowDetailApproval;

    View v;
    Dialog dialogAskingBeforeApproval;
    Button btnAdminApprovalOKDialog;
    Button btnAdminApprovalCancelDialog;
    TextView txtAdminAskBeforeApproval;
    boolean isApproval;
    CompanyWaitingApprovalModel model;

    public AdminApprovalViewHolder(@NonNull final View itemView) {
        super(itemView);
        txtCompanyNameSendApproval = itemView.findViewById(R.id.txtCompanyNameSendApproval);
        txtDateSendApproval = itemView.findViewById(R.id.txtDateSendApproval);
        imgbtnCheckApproval = itemView.findViewById(R.id.imgbtnCheckApproval);
        imgbtnUncheckApproval = itemView.findViewById(R.id.imgbtnUncheckApproval);
        imgbtnShowDetailApproval = itemView.findViewById(R.id.imgbtnShowDetailApproval);
        btnAdminApprovalOKDialog = itemView.findViewById(R.id.btnAdminApprovalOKDialog);
        btnAdminApprovalCancelDialog = itemView.findViewById(R.id.btnAdminApprovalCancelDialog);
    }

}
