package com.example.team32gb.jobit.View.Evaluation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.team32gb.jobit.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRatingMemAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<RatingMem> arr;

    public CustomRatingMemAdapter(Context context, ArrayList<RatingMem> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        if(arr==null){
        return 0;
        }
        else return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.row_ratingmem,null);

        TextView rate=row.findViewById(R.id.txtMemRatingTitle);
        RatingBar ratingBar=row.findViewById((R.id.MemRatingBar));
        final TextView rateBarValue=row.findViewById(R.id.txtMemRatingValue);

        rate.setText(arr.get(position).getTitle());
        ratingBar.setRating(arr.get(position).getRate());
        rateBarValue.setText(Float.toString(arr.get(position).getRate()));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateBarValue.setText(Float.toString(rating));
            }
        });

        return row;
    }
}
