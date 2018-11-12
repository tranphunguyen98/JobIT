package com.example.team32gb.jobit.View.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team32gb.jobit.R;

import java.util.ArrayList;
import java.util.List;

public class AdminReportAdapter extends RecyclerView.Adapter<AdminReportAdapter.ViewHolder> {
    List<AdminReportModel> arrReport = new ArrayList<AdminReportModel>();
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNameAccused;
        TextView txtDateTime;
        LinearLayout llItemReport;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemView =
            txtNameAccused= itemView.findViewById(R.id.txtNameAccused);
            txtDateTime = itemView.findViewById(R.id.txtDateTimeReport);
            llItemReport=itemView.findViewById(R.id.llItemReport);
        }
    }

    public AdminReportAdapter() {
    }

    public AdminReportAdapter(Context context) {
        this.context = context;
    }

    public AdminReportAdapter(List<AdminReportModel> arrReport) {
        this.arrReport = arrReport;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.admin_report_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        AdminReportModel reportModel = arrReport.get(position);
        viewHolder.txtNameAccused.setText(reportModel.getNameAccused());
        viewHolder.txtDateTime.setText(reportModel.getDateTime());
    }

    @Override
    public int getItemCount() {
        return arrReport.size();
    }

    public void initDataTest(){
        arrReport.add(new AdminReportModel("id1", "id21", "Tran A1", "Tran B1", "Decription 1", "2/1/2000", true));
        arrReport.add(new AdminReportModel("id2", "id22", "Tran A2", "Tran B2", "Decription 1", "2/12/2000", true));
        arrReport.add(new AdminReportModel("id3", "id23", "Tran A3", "Tran B3", "Decription 1", "2/13/2000", true));
        arrReport.add(new AdminReportModel("id4", "id24", "Tran A4", "Tran B4", "Decription 1", "2/14/2000", true));
        arrReport.add(new AdminReportModel("id5", "id25", "Tran A5", "Tran B5", "Decription 1", "2/15/2000", true));
        arrReport.add(new AdminReportModel("id6", "id26", "Tran A6", "Tran B6", "Decription 1", "2/16/2000", true));
        arrReport.add(new AdminReportModel("id7", "id27", "Tran A7", "Tran B7", "Decription 1", "2/17/2000", true));
        arrReport.add(new AdminReportModel("id8", "id28", "Tran A8", "Tran B8", "Decription 1", "2/18/2000", true));
        arrReport.add(new AdminReportModel("id9", "id29", "Tran A9", "Tran B9", "Decription 1", "2/19/2000", true));
        arrReport.add(new AdminReportModel("id10", "id30", "Tran A11", "Tran B11", "Decription 1", "2/11/2000", true));
        arrReport.add(new AdminReportModel("id11", "id31", "Tran A12", "Tran B12", "Decription 1", "2/12/2000", true));
        this.notifyDataSetChanged();
    }

}
