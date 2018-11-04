package com.example.team32gb.jobit.Model.JobSeekerProfile;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Presenter.JobSeekerProfile.JobSeekerInterface;
import com.example.team32gb.jobit.Utility.Config;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ModelJobSeekerProfile {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReferenceImage;
    private DatabaseReference databaseReference;
    private UserModel userModel;
    GreenRobotEventBus eventBus;
    String TAG = "kiemtraUpload";

    public ModelJobSeekerProfile(String uid) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReferenceImage = firebaseStorage.getReference().child(Config.REF_FOLDER_AVATAR).child(uid);
        databaseReference = firebaseDatabase.getReference().child(Config.REF_JOBSEEKERS_NODE).child(uid);
        eventBus = GreenRobotEventBus.getInstance();
        userModel = new UserModel();
    }

    public void getUserModel(final JobSeekerInterface jobSeekerInterface) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    userModel = dataSnapshot.getValue(UserModel.class);
                }
                jobSeekerInterface.getUserModel(userModel);
                eventBus.post(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public UserModel getUserModelLocal() {

        return userModel;
    }

    public StorageReference getStorageReferenceImage() {
        return storageReferenceImage;
    }

    public void saveNameProfile(String name) {
        databaseReference.child("name").setValue(name).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public void savePictureProfile(final Uri uri, String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StorageTask task = storageReferenceImage.putFile(uri);
                task.addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.e("kiemtraUpload",  "onSuccess: " );
                        userModel.setAvatar(storageReferenceImage.getPath());
                        databaseReference.child("avatar").setValue(storageReferenceImage.getPath());
                    }
                });

            }
        }).start();
    }
    public void savePictureProfile(final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = storageReferenceImage.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        userModel.setAvatar(storageReferenceImage.getPath());
                        databaseReference.child("avatar").setValue(storageReferenceImage.getPath());
                    }
                });
            }
        }).start();

    }

}
