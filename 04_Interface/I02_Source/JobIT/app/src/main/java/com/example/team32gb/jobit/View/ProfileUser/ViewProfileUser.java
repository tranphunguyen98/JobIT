package com.example.team32gb.jobit.View.ProfileUser;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;

public interface ViewProfileUser {
    public void showProfile(UserModel userModel, Bitmap bitmap);
    public void showProfile(UserModel userModel, Uri uri);
}
