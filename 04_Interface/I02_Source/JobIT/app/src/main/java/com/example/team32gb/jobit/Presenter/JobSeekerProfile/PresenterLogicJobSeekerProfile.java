package com.example.team32gb.jobit.Presenter.JobSeekerProfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.JobSeekerProfile.ModelJobSeekerProfile;
import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.View.ProfileUser.ViewProfileUser;
import com.google.android.gms.tasks.OnSuccessListener;

import org.greenrobot.eventbus.Subscribe;

public class PresenterLogicJobSeekerProfile implements PresenterInJobSeekerProfile {
    private ModelJobSeekerProfile modelJobSeekerProfile;
    private ViewProfileUser view;
    private EventBus eventBus;

    public PresenterLogicJobSeekerProfile(ViewProfileUser view, String uid) {
        modelJobSeekerProfile = new ModelJobSeekerProfile();
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
    public void saveNameProfile(String refUser, String uid, String name) {
        modelJobSeekerProfile.saveNameProfile(refUser,uid,name);
    }

    @Override
    public void saveImageProfile(Uri uri, String type) {
        //modelJobSeekerProfile.savePictureProfile(uri, type);
    }

    @Override
    public void saveImageProfile(String refUser, String uid,Bitmap bitmap) {
        modelJobSeekerProfile.savePictureProfile(refUser,uid,bitmap);
    }

    @Override
    public void getProfile(String refUser, String uid) {
        modelJobSeekerProfile.getUserModel(refUser,uid);
    }

    @Override
    public void getProfileLocal() {
//        view.showProfile(modelJobSeekerProfile.getUserModelLocal(), modelJobSeekerProfile);
    }

    @Override
    @Subscribe
    public void showProfile(final UserModel userModel) {
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
