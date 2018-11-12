package com.example.team32gb.jobit.View.ListJob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Utility.Util;
import com.example.team32gb.jobit.View.JobDetail.DetailJobActivity;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.ListJobSearch.ItemClickListener;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ListJobViewAdapter extends RecyclerView.Adapter<ListJobViewAdapter.MyViewHolder> {

    Context context;
    List<ItemPostJob> mdata;

    public ListJobViewAdapter(Context context, List<ItemPostJob> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        Log.e("kiemtraid", "oncreateViewHolder" + i);
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.activity_item_listjob, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Log.e("kiemtraid", "onBindViewHolder" + i);
        myViewHolder.txtNameJob.setText(mdata.get(i).getDataPostJob().getNameJob());
        myViewHolder.txtNameCompany.setText(mdata.get(i).getNameCompany());
        myViewHolder.txtTime.setText(Util.getSubTime(mdata.get(i).getDataPostJob().getTime()));
        String minSalary = mdata.get(i).getDataPostJob().getMinSalary();
        String maxSalary = mdata.get(i).getDataPostJob().getMaxSalary();
        myViewHolder.txtSalary.setText("Từ $" + minSalary + " đến $" + maxSalary);
        myViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(context.getApplicationContext(), DetailJobActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("bundle",mdata.get(i));
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtNameJob;
        private TextView txtNameCompany;
        private TextView txtTime;
        private TextView txtSalary;
        private MaterialCardView item_listjob;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNameJob = itemView.findViewById(R.id.txtTenCV);
            txtNameCompany = itemView.findViewById(R.id.txtTenCT);
            txtTime = itemView.findViewById(R.id.txtThơiGian);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            item_listjob = itemView.findViewById(R.id.item_listjob);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
