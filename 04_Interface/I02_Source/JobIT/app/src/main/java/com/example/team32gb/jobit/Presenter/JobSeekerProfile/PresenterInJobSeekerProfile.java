package com.example.team32gb.jobit.Presenter.JobSeekerProfile;


import android.net.Uri;

public interface PresenterInJobSeekerProfile {
    public void saveNameProfile(String name);
    public void saveImageProfile(Uri uri,String type);
    public void getProfile();
}
