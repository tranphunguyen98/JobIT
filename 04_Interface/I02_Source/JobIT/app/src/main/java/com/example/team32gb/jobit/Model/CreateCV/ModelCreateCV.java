package com.example.team32gb.jobit.Model.CreateCV;

import android.support.annotation.NonNull;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.Utility.Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ModelCreateCV {
    private CVEmployeeModel cvEmployeeModel;
    private List<ProjectInCVModel> projectInCVModels;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    GreenRobotEventBus eventBus;

    String TAG = "kiemtraUpload";

    public ModelCreateCV() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(Config.REF_CVS_NODE);
        eventBus = GreenRobotEventBus.getInstance();
    }
    public void getCVFromUid (String uid){
        databaseReference = databaseReference.child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void saveCV(String uid, CVEmployeeModel cvEmployeeModel, List<ProjectInCVModel> projectInCVModels) {
        DatabaseReference dfCV = databaseReference.child(uid);
        dfCV.setValue(cvEmployeeModel);
//        DatabaseReference dfProject1 = dfCV.child("projects").child("project1");
//        dfProject1.setValue(projects.get(0));
//        DatabaseReference dfProject2 = dfCV.child("projects").child("project2");
//        dfProject2.setValue(projects.get(1));

    }

}
