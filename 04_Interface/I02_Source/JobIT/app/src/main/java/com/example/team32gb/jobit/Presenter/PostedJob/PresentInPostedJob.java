package com.example.team32gb.jobit.Presenter.PostedJob;

import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;

import java.util.List;

public interface PresentInPostedJob {
    void onCreate();
    void onDestroy();
    void getPost(String Uid);
    void showPost(List<ItemPostJob> itemPostJobs);
}
