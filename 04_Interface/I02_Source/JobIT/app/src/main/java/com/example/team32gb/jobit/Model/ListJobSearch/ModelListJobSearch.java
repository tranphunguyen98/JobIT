package com.example.team32gb.jobit.Model.ListJobSearch;

import androidx.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelListJobSearch {
    List<ItemPostJob> itemPostJobs;
    GreenRobotEventBus eventBus;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ModelListJobSearch() {
        itemPostJobs = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void getListJob(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dsTin = dataSnapshot.child("tinTuyenDungs");
                for(DataSnapshot snapshot: dsTin.getChildren()) {
                    for(DataSnapshot snapshotChild : snapshot.getChildren()) {
                        DataPostJob dataPostJob = snapshotChild.getValue(DataPostJob.class);
                        ItemPostJob itemPostJob = new ItemPostJob();
                        itemPostJob.setDataPostJob(dataPostJob);
                        itemPostJob.setIdJob(snapshotChild.getKey());
                        itemPostJob.setIdCompany(snapshot.getKey());
                        Log.e("kiemtraname",snapshot.getKey());
                        String nameCompany = dataSnapshot.child("companys").child(itemPostJob.getIdCompany()).child("name").getValue(String.class);

                        itemPostJob.setNameCompany(nameCompany);

                        itemPostJobs.add(itemPostJob);
                    }
                }
                eventBus.post(itemPostJobs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
