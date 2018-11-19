package com.example.team32gb.jobit.View.Admin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team32gb.jobit.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdminApprovalViewHolder extends RecyclerView.ViewHolder {
    public interface ClickListner{
        public void onItemClick(View view, int position);
        }

    TextView txtCompanyNameSendApproval;
    TextView txtDateSendApproval;
    ImageButton imgbtnUncheckApproval;
    ImageButton imgbtnCheckApproval;
    ImageButton imgbtnShowDetailApproval;

    Button btnAdminApprovalOKDialog;
    Button btnAdminApprovalCancelDialog;
    AdminApprovalViewHolder.ClickListner mClickListner;

    public AdminApprovalViewHolder(@NonNull final View itemView) {
        super(itemView);
        txtCompanyNameSendApproval = itemView.findViewById(R.id.txtCompanyNameSendApproval);
        txtDateSendApproval = itemView.findViewById(R.id.txtDateSendApproval);
        imgbtnCheckApproval = itemView.findViewById(R.id.imgbtnCheckApproval);
        imgbtnUncheckApproval = itemView.findViewById(R.id.imgbtnUncheckApproval);
        imgbtnShowDetailApproval = itemView.findViewById(R.id.imgbtnShowDetailApproval);
        btnAdminApprovalOKDialog = itemView.findViewById(R.id.btnAdminApprovalOKDialog);
        btnAdminApprovalCancelDialog = itemView.findViewById(R.id.btnAdminApprovalCancelDialog);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListner.onItemClick(v, getAdapterPosition());
            }
        });
    }
    public void SetOnClickListener(AdminApprovalViewHolder.ClickListner clickListner){
        this.mClickListner = clickListner;
    }

}
