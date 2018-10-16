package com.example.team32gb.jobit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListJobViewAdapter extends RecyclerView.Adapter<ListJobViewAdapter.MyViewHolder> {

    Context context;
    List<DataJob> mdata;

    public ListJobViewAdapter(Context context, List<DataJob> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.activity_item_listjob,viewGroup,false);
        final MyViewHolder viewHolder = new MyViewHolder(v);
        viewHolder.item_listjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context.getApplicationContext(),DetailJobActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtNameJob.setText(mdata.get(i).getNameJob());
        myViewHolder.txtNameCompany.setText(mdata.get(i).getNameCompany());
        myViewHolder.txtTime.setText((mdata.get(i).getTime()));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtNameJob;
        private TextView txtNameCompany;
        private TextView txtTime;
        private RelativeLayout item_listjob;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameJob = itemView.findViewById(R.id.txtTenCV);
            txtNameCompany = itemView.findViewById(R.id.txtTenCT);
            txtTime = itemView.findViewById(R.id.txtThơiGian);
            item_listjob = itemView.findViewById(R.id.item_listjob);
        }
    }
}
