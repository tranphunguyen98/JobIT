package com.example.team32gb.jobit.Presenter.InviteJob;

import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;

import java.util.List;

public interface PresenterInInviteJob {
    void onCreate();
    void onDestroy();
    void getListJob(String uid);
    void showListJob(List<ItemPostJob> itemPostJobs);
}
