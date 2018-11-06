package com.example.team32gb.jobit.Model.EmployerAppliedPosted;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelEmployerAppliedPosted {
    private String strTime;
    private GreenRobotEventBus eventBus;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public ModelEmployerAppliedPosted() {
        strTime = "";
        eventBus = GreenRobotEventBus.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Applied");
    }
}
