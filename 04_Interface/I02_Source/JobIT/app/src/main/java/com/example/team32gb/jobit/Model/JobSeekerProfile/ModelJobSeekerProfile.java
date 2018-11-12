package com.example.team32gb.jobit.Model.JobSeekerProfile;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
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
    private StorageReference srAvatar;
    private DatabaseReference nodeRoot;
    private UserModel userModel;
    GreenRobotEventBus eventBus;
    String TAG = "kiemtraUpload";

    public ModelJobSeekerProfile() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        srAvatar = firebaseStorage.getReference().child(Config.REF_FOLDER_AVATAR);
        nodeRoot = firebaseDatabase.getReference();
        eventBus = GreenRobotEventBus.getInstance();
        userModel = new UserModel();
    }

    public void getUserModel(String refUser,String uid) {

        nodeRoot.child(refUser).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    userModel = dataSnapshot.getValue(UserModel.class);
                    eventBus.post(userModel);
                }
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
        return srAvatar;
    }

    public void saveNameProfile(String refUser, String uid,String name) {
        nodeRoot.child(refUser).child(uid).child("name").setValue(name).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public void savePictureProfile(final String refUsre, final String uid, final Uri uri) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                StorageTask task = srAvatar.putFile(uri);
//                task.addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        Log.e("kiemtraUpload",  "onSuccess: " );
//                        userModel.setAvatar(srAvatar.getPath());
//                        nodeRoot.child(refUsre).child(uid).child("avatar").setValue(srAvatar.getPath());
//                    }
//                });
//
//            }
//        }).start();
    }
    public void savePictureProfile(final String refUsre, final String uid,final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StorageReference storageReferenceImage = srAvatar.child(uid);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = storageReferenceImage.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        userModel.setAvatar(storageReferenceImage.getPath());
                        nodeRoot.child(refUsre).child(uid).child("avatar").setValue(storageReferenceImage.getPath());
                    }
                });
            }
        }).start();

    }

}
