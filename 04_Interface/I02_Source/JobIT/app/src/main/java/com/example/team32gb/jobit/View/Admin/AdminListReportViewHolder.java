package com.example.team32gb.jobit.View.Admin;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.team32gb.jobit.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdminListReportViewHolder extends RecyclerView.ViewHolder {
    public interface ClickListener{
        public void onItemClick(View view, int position);
    }

     TextView txtName;
     TextView txtDateSendReport;
     ImageButton ibtnSendWarningReport;
     ImageButton ibtnIgnoreReportJobSeeker;
    private AdminListReportViewHolder.ClickListener mClickListener;

    public AdminListReportViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName  = itemView.findViewById(R.id.txtNameAccused);
        txtDateSendReport = itemView.findViewById(R.id.txtDateTimeReport);
        ibtnSendWarningReport = itemView.findViewById(R.id.ibtnSendWarningReportToJobSeeker);
        ibtnIgnoreReportJobSeeker = itemView.findViewById(R.id.ibtnIgnoreReportJobSeeker);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
    }

    public void SetOnClickListener(AdminListReportViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
