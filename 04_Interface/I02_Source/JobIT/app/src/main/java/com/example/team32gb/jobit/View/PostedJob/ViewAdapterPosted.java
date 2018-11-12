package com.example.team32gb.jobit.View.PostedJob;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.team32gb.jobit.ListCandidateAcvitity;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.JobDetail.DetailJobActivity;
import com.example.team32gb.jobit.View.ListJobSearch.ItemClickListener;

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
        myViewHolder.txtNameJob.setText(itemPostJobs.get(i).getDataPostJob().getNameJob());
        myViewHolder.txtTime.setText((itemPostJobs.get(i).getDataPostJob().getTime()));

        myViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(context.getApplicationContext(),DetailJobActivity.class);
                intent.putExtra("bundle",itemPostJobs.get(i));
                context.getApplicationContext().startActivity(intent);
            }
        });
        myViewHolder.btnApplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ListCandidateAcvitity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameJob",itemPostJobs.get(i).getDataPostJob().getNameJob());
                Log.e("kiemtratime",itemPostJobs.get(i).getDataPostJob().getTime());
                bundle.putString("timeJob",itemPostJobs.get(i).getDataPostJob().getTime());
                bundle.putString("idCompany",itemPostJobs.get(i).getIdCompany());
                bundle.putString("idJob",itemPostJobs.get(i).getIdJob());
                intent.putExtra("bundle",bundle);
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
        private Button btnApplied;

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
            btnApplied = itemView.findViewById(R.id.btnApplied);
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
