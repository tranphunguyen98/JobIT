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
                DataSnapshot dfDaLuu = dataSnapshot.child("daLuus").child(uid);
                for(DataSnapshot snapshotCompany: dfDaLuu.getChildren()) {
                    for (DataSnapshot snJob : snapshotCompany.getChildren()) {
                        ItemPostJob itemPostJob = new ItemPostJob();
                        String timeSaved = snJob.child("timeSaved").getValue(String.class);
                        itemPostJob.setTimeApplied(timeSaved);
                        String idJob = snJob.getKey();
                        String idCompany = snapshotCompany.getKey();
                        Log.e("kiemtraApply", idJob + " : " + idCompany);
                        DataPostJob dataPostJob = dataSnapshot.child("tinTuyenDungs").child(idCompany).child(idJob).getValue(DataPostJob.class);
                        if (dataPostJob != null) {
                            Log.e("kiemtraApply", dataPostJob.getNameJob());
                        } else {
                            Log.e("kiemtraApply", "fail1");

                        }
                        itemPostJob.setDataPostJob(dataPostJob);
                        Log.e("kiemtraApply", itemPostJob.getDataPostJob().getIdCompany() + " : " + itemPostJob.getDataPostJob().getIdJob());
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
