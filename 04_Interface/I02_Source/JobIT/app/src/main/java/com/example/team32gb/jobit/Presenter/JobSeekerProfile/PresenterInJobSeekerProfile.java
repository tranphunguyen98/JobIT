package com.example.team32gb.jobit.Presenter.JobSeekerProfile;


import android.graphics.Bitmap;
import android.net.Uri;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;

public interface PresenterInJobSeekerProfile {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    public void saveNameProfile(String refUser, String uid,String name);
    public void saveImageProfile(Uri uri,String type);
    public void saveImageProfile(String refUser, String uid,Bitmap bitmap);
    public void showProfile(final UserModel userModel);

    public void getProfile(String refUser, String uid);
    public void getProfileLocal();
}
