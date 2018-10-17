package com.example.team32gb.jobit;


import android.content.Context;
import android.print.PrintDocumentAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdaptorProjectInCV extends BaseAdapter {
    ArrayList<ProjectInCV> item = new ArrayList<>();
    LayoutInflater inflater;
    TextView tvNameProject;

    static class ViewHolder{
        TextView txtNamePro;
    }

    ViewHolder holder;

    public CustomAdaptorProjectInCV(ArrayList<ProjectInCV> items, Context context){
        this.item=items;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public CustomAdaptorProjectInCV(Context context){
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<ProjectInCV> getItem() {
        return item;
    }

    @Override
    public int getCount() {
        return item.size() - 1;
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.custom_listview_project_incv, null);
            holder = new ViewHolder();
            holder.txtNamePro = convertView.findViewById(R.id.txtNameProject);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.txtNamePro.setText(item.get(position).getName());
        return convertView;
    }

    Boolean addItem(ProjectInCV newItem){
        if (item.add(newItem)){
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    void deleteItem(int position){
        if (item != null && position >= 0 && (position < item.size()-1)){
            item.remove(position);
        }
    }


}

