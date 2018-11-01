package com.example.team32gb.jobit.Model.ListJobSearch;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelListJobSearch {
    List<ItemJob> itemJobs;
    GreenRobotEventBus eventBus;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ModelListJobSearch() {
        itemJobs = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("tinTuyenDungs");
    }

    public void getListJob(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    for(DataSnapshot snapshotChild : snapshot.getChildren()) {
                        DataJob dataJob = snapshotChild.getValue(DataJob.class);
                        ItemJob itemJob = new ItemJob(dataJob);
                        itemJob.setIdJob(snapshotChild.getKey());
                        itemJob.setIdCompany(snapshot.getKey());
                        Log.e("kiemtraSnap", dataJob.getNameJob());
                        itemJobs.add(itemJob);
                    }
                }
                eventBus.post(itemJobs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
