package com.example.team32gb.jobit.View.PostedJob;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.team32gb.jobit.ListCandidateAppliedActivity;
import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.JobDetail.DetailJobActivity;
import com.example.team32gb.jobit.View.ListJobSearch.ItemClickListener;

import java.io.Serializable;
import java.util.List;

public class ViewAdapterPosted extends RecyclerView.Adapter<ViewAdapterPosted.MyViewHolder> {

    Context context;
    List<ItemPostJob> itemPostJobs;


    public ViewAdapterPosted(Context context, List<ItemPostJob> mdata) {
        this.context = context;
        this.itemPostJobs = mdata;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_list_posted, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapterPosted.MyViewHolder myViewHolder, final int i) {
        myViewHolder.txtNameJob.setText(itemPostJobs.get(i).getNameJob());
        myViewHolder.txtNameCompany.setText(itemPostJobs.get(i).getNameCompany());
        myViewHolder.txtTime.setText((itemPostJobs.get(i).getTime()));

        myViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(context.getApplicationContext(),DetailPostedJobActivity.class);
                Bundle bundle = new Bundle();
                // Log.e("kiemtraid", position + "");

                bundle.putString("idJob", itemPostJobs.get(position).getIdJob());
                bundle.putString("idCompany", itemPostJobs.get(position).getIdCompany());

                bundle.putString("nameCompany",itemPostJobs.get(position).getNameCompany());
                bundle.putString("nameJob", itemPostJobs.get(position).getNameJob());
                bundle.putString("typeJob", itemPostJobs.get(position).getTypeJob());
                bundle.putString("minSalary", itemPostJobs.get(position).getMinSalary());
                bundle.putString("maxSalary", itemPostJobs.get(position).getMaxSalary());
                bundle.putString("numberEmployer", itemPostJobs.get(position).getNumberEmployer());
                bundle.putString("description", itemPostJobs.get(position).getDescription());
                bundle.putString("qualification", itemPostJobs.get(position).getQualification());
                bundle.putString("each", itemPostJobs.get(position).getEach());

                intent.putExtra("bundle", bundle);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemPostJobs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNameJob;
        private TextView txtNameCompany;
        private TextView txtTime;
        private Button btnDelete;

        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            txtNameJob = itemView.findViewById(R.id.txtNameJob);
            txtNameCompany = itemView.findViewById(R.id.txtNameCompany);
            txtTime = itemView.findViewById(R.id.txtTime);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
