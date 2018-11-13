package com.example.team32gb.jobit.Model.SavedJob;

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

import androidx.annotation.NonNull;

public class ModelSavedJob {
    List<ItemPostJob> itemPostJobs;
    GreenRobotEventBus eventBus;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ModelSavedJob() {
        itemPostJobs = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void getListJob(final String uid){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dfChoPhongVan = dataSnapshot.child("daLuus").child(uid);
                for(DataSnapshot snapshotCompany: dfChoPhongVan.getChildren()) {
                    for (DataSnapshot snJob : snapshotCompany.getChildren()) {
                        ItemPostJob itemPostJob = new ItemPostJob();
                        itemPostJob.setIdJob(snJob.getKey());
                        itemPostJob.setIdCompany(snapshotCompany.getKey());
                        String timeApplied = snJob.child("timeApplied").getValue(String.class);
                        itemPostJob.setTimeApplied(timeApplied);
                        String idJob = snJob.getKey();
                        Log.e("kiemtraApply", idJob);
                        String nameCompany = dataSnapshot.child("companys").child(itemPostJob.getIdCompany()).child("name").getValue(String.class);

                        itemPostJob.setNameCompany(nameCompany);
                        DataPostJob dataPostJob = dataSnapshot.child("tinTuyenDungs").child(itemPostJob.getIdCompany()).child(itemPostJob.getIdJob()).getValue(DataPostJob.class);
                        if (dataPostJob != null) {
                            Log.e("kiemtraApply", dataPostJob.getNameJob());
                        } else {
                            Log.e("kiemtraApply", "fail1");

                        }
                        itemPostJob.setDataPostJob(dataPostJob);
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
