package com.example.team32gb.jobit.Presenter.JobSeekerProfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.JobSeekerProfile.ModelJobSeekerProfile;
import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.View.JobSeekerProfile.ViewJobSeekerProfile;
import com.google.android.gms.tasks.OnSuccessListener;

import org.greenrobot.eventbus.Subscribe;

public class PresenterLogicJobSeekerProfile implements PresenterInJobSeekerProfile {
    private ModelJobSeekerProfile modelJobSeekerProfile;
    private ViewJobSeekerProfile view;
    private EventBus eventBus;

    public PresenterLogicJobSeekerProfile(ViewJobSeekerProfile view, String uid) {
        modelJobSeekerProfile = new ModelJobSeekerProfile(uid);
        this.eventBus =GreenRobotEventBus.getInstance();
        this.view = view;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);

    }

    @Override
    public void saveNameProfile(String name) {
        modelJobSeekerProfile.saveNameProfile(name);
    }

    @Override
    public void saveImageProfile(Uri uri, String type) {
        modelJobSeekerProfile.savePictureProfile(uri, type);
    }

    @Override
    public void saveImageProfile(Bitmap bitmap) {
        modelJobSeekerProfile.savePictureProfile(bitmap);
    }

    @Override
    public void getProfile() {
        modelJobSeekerProfile.getUserModel(new JobSeekerInterface() {
            @Override
            public void getUserModel(final UserModel userModel) {
                modelJobSeekerProfile.getStorageReferenceImage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //view.showProfile(userModel,uri);
                    }
                });
//                long ONE_MEGABYTE = 1024 * 1024;
//                modelJobSeekerProfile.getStorageReferenceImage().getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                    @Override
//                    public void onSuccess(byte[] bytes) {
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                        view.showProfile(userModel,bitmap);
//                    }
//
//                });
            }
        });
    }

    @Override
    public void getProfileLocal() {
//        view.showProfile(modelJobSeekerProfile.getUserModelLocal(), modelJobSeekerProfile);
    }

    @Override
    @Subscribe
    public void showProfile(final UserModel userModel) {
        Log.e("kiemtraEventBus",userModel.getEmail());
        long ONE_MEGABYTE = 1024 * 1024;
                modelJobSeekerProfile.getStorageReferenceImage().getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        view.showProfile(userModel, bitmap);
                    }
                });
    }


}
