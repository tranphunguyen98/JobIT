package com.example.team32gb.jobit;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.ObjectClass.DataJob;

import java.util.List;

public class ViewAdapterPosted extends RecyclerView.Adapter<ViewAdapterPosted.MyViewHolder> {

    Context context;
    List<DataJob> mdata;


    public ViewAdapterPosted(Context context, List<DataJob> mdata) {
        this.context = context;
        this.mdata = mdata;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_list_posted, viewGroup, false);
       MyViewHolder viewHolder = new MyViewHolder(v);
       viewHolder.btnApplied.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context.getApplicationContext(),ListCandidateAppliedActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
               context.getApplicationContext().startActivity(intent);
           }
       });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapterPosted.MyViewHolder myViewHolder, int i) {
        myViewHolder.txtNameJob.setText(mdata.get(i).getNameJob());
        myViewHolder.txtNameCompany.setText(mdata.get(i).getNameCompany());
        myViewHolder.txtTime.setText((mdata.get(i).getTime()));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNameJob;
        private TextView txtNameCompany;
        private TextView txtTime;
        private Button btnDelete, btnEdit, btnApplied;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameJob = itemView.findViewById(R.id.txtNameJob);
            txtNameCompany = itemView.findViewById(R.id.txtNameCompany);
            txtTime = itemView.findViewById(R.id.txtTime);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnApplied = itemView.findViewById(R.id.btnApplied);
        }
    }
}
