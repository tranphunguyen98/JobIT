package com.example.team32gb.jobit.Model.AdminStatistic;

import android.app.ProgressDialog;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_RECRUITERS_NODE;

public class AdminStatisticModel {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    long count=0;

    public AdminStatisticModel() {
    }

    public long statisticEmployee(final TextView tv, final ProgressDialog dialog){
        ref.child(REF_JOBSEEKERS_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                tv.setText(String.valueOf(count));
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return count;
    }

    public long statisticRecruiter(final TextView tv, final ProgressDialog dialog){
        ref.child(REF_RECRUITERS_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                tv.setText(String.valueOf(count));
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return count;
    }
}
