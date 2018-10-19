package com.example.team32gb.jobit.Model.JobSeekerProfile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Presenter.JobSeekerProfile.JobSeekerInterface;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.PresenterInJobSeekerProfile;
import com.example.team32gb.jobit.Utility.Config;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModelJobSeekerProfile {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public ModelJobSeekerProfile() {
        firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(Config.REF_USERS_NODE).child(uid);
    }

    public void getUserModel(final JobSeekerInterface jobSeekerInterface){
        ValueEventListener listener = new ValueEventListener() {
            UserModel userModel;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null) {
                    userModel = new UserModel();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    userModel.setEmail(user.getEmail());
                }
                else {
                    userModel = dataSnapshot.getValue(UserModel.class);
                }
                jobSeekerInterface.getUserModel(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(listener);
    }

}
