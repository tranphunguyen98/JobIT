package com.example.team32gb.jobit.Presenter.SavedJob;

import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Model.SavedJob.ModelSavedJob;
import com.example.team32gb.jobit.View.SavedJob.ViewListSavedJob;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PresenterSavedJob implements PresenterInSavedJob {
    ViewListSavedJob view;
    ModelSavedJob model;
    GreenRobotEventBus eventBus;

    public PresenterSavedJob(ViewListSavedJob view){
        this.view = view;
        model = new ModelSavedJob();
        eventBus = GreenRobotEventBus.getInstance();
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
    public void getListJob(String uid) {
        model.getListJob(uid);
    }

    @Override
    @Subscribe
    public void showListJob(List<ItemPostJob> itemPostJobs) {
        Log.e("kiemtraapplied",itemPostJobs.get(0).getDataPostJob().getNameJob());
        view.showListJob(itemPostJobs);
    }

}
