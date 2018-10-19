package com.example.team32gb.jobit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ViewAdapterApplied extends RecyclerView.Adapter<ViewAdapterApplied.MyViewHolder>{

    Context context;
    List<DataApplied> mData;

    public ViewAdapterApplied(Context context,List<DataApplied> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View v = LayoutInflater.from(context).inflate(R.layout.item_list_applied,viewGroup,false);
      return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtName.setText(mData.get(i).getName());
        myViewHolder.txtTime.setText(mData.get(i).getDayApplied());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtTime;
        private Button btnDelete, btnEdit, btnWacthCV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtTime = itemView.findViewById(R.id.txtTime);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnWacthCV = itemView.findViewById(R.id.btnWatchCV);
        }
    }
}
