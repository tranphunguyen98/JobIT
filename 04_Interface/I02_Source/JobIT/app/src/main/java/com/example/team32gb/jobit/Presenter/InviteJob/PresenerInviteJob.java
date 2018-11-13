package com.example.team32gb.jobit.Presenter.InviteJob;

import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.InviteJob.ModelInviteJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Model.WaitingForInterview.ModelInterview;
import com.example.team32gb.jobit.Presenter.WaitingForInterview.PresenterInInterview;
import com.example.team32gb.jobit.View.InviteJob.ViewListJobInvite;
import com.example.team32gb.jobit.View.WaitingForInterview.ViewListJobInterview;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PresenerInviteJob implements PresenterInInviteJob {
    ViewListJobInvite view;
    ModelInviteJob model;
    GreenRobotEventBus eventBus;

    public PresenerInviteJob(ViewListJobInvite view){
        this.view = view;
        model = new ModelInviteJob();
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
