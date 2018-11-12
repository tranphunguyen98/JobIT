package com.example.team32gb.jobit.Model.Applied;

import androidx.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelApplied {
    List<ItemJobApplied> itemJobApplieds;
    GreenRobotEventBus eventBus;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ModelApplied() {
        itemJobApplieds = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void getListJob(final String uid){
        Log.e("kiemtraApply","a23" + uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dfApplieds = dataSnapshot.child("Applieds").child(uid);
                for(DataSnapshot snapshotCompany: dfApplieds.getChildren()) {
                    for(DataSnapshot snJob : snapshotCompany.getChildren()) {
                        ItemJobApplied itemJobApplied =new ItemJobApplied();
                        itemJobApplied.setIdJob(snJob.getKey());
                        itemJobApplied.setIdCompany(snapshotCompany.getKey());
                        String timeApplied = snJob.child("timeAppiled").getValue(String.class);
                        itemJobApplied.setTimeApply(timeApplied);
                        String idJob = snJob.getKey();
                        Log.e("kiemtraApply",idJob);
                        DataJob dataJob = dataSnapshot.child("tinTuyenDungs").child(itemJobApplied.getIdCompany()).child(itemJobApplied.getIdJob()).getValue(DataJob.class);
                        itemJobApplied.setDataJob(dataJob);
                        itemJobApplieds.add(itemJobApplied);
                    }
                }
                eventBus.post(itemJobApplieds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
