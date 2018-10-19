package com.example.team32gb.jobit.Presenter.JobSeekerProfile;

import android.util.Log;

import com.example.team32gb.jobit.Model.JobSeekerProfile.ModelJobSeekerProfile;
import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.View.JobSeekerProfile.ViewJobSeekerProfile;

public class PresenterLogicJobSeekerProfile implements  PresenterInJobSeekerProfile {
    private ModelJobSeekerProfile modelJobSeekerProfile;
    private ViewJobSeekerProfile view;

    public PresenterLogicJobSeekerProfile(ViewJobSeekerProfile view) {
        modelJobSeekerProfile = new ModelJobSeekerProfile();
        this.view = view;
    }

    @Override
    public void getProfile() {
        JobSeekerInterface jobSeekerInterface = new JobSeekerInterface() {
            @Override
            public void getUserModel(UserModel userModel) {
                Log.e("kiemtraModel",userModel.getEmail());
                view.showProfile(userModel);
            }
        };
        Log.e("kiemtraModel","abc");
        modelJobSeekerProfile.getUserModel(jobSeekerInterface);
    }
}
