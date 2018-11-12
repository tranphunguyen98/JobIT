package com.example.team32gb.jobit.Model.PostedJob;

import androidx.annotation.NonNull;

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

public class ModelPostedJob {
    private List<ItemPostJob> itemPostJobs;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    GreenRobotEventBus eventBus;
    public ModelPostedJob() {
        itemPostJobs = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("tinTuyenDungs");

    }
    public void getPost(String Uid){
        databaseReference.child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                       DataPostJob dataPostJob = snapshot.getValue(DataPostJob.class);
                       ItemPostJob itemPostJob = new ItemPostJob();
                       itemPostJob.setDataPostJob(dataPostJob);
                       itemPostJob.setIdJob(snapshot.getKey());
                       itemPostJob.setIdCompany(dataSnapshot.getKey());
                       itemPostJobs.add(itemPostJob);
               }
               eventBus.post(itemPostJobs);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
