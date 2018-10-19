package com.example.team32gb.jobit.Presenter.JobSeekerProfile;

import android.net.Uri;

import com.example.team32gb.jobit.Model.JobSeekerProfile.ModelJobSeekerProfile;
import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.View.JobSeekerProfile.ViewJobSeekerProfile;

public class PresenterLogicJobSeekerProfile implements  PresenterInJobSeekerProfile {
    private ModelJobSeekerProfile modelJobSeekerProfile;
    private ViewJobSeekerProfile view;

    public PresenterLogicJobSeekerProfile(ViewJobSeekerProfile view,String uid) {
        modelJobSeekerProfile = new ModelJobSeekerProfile(uid);
        this.view = view;
    }


    @Override
    public void saveNameProfile(String name) {
        modelJobSeekerProfile.saveNameProfile(name);
    }

    @Override
    public void saveImageProfile(Uri uri,String type) {
        modelJobSeekerProfile.savePictureProfile(uri,type);
    }

    @Override
    public void getProfile() {
        modelJobSeekerProfile.getUserModel(new JobSeekerInterface() {
            @Override
            public void getUserModel(UserModel userModel) {
                view.showProfile(userModel);
            }
        });
    }
}
