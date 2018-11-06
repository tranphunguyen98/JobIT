package com.example.team32gb.jobit.Presenter.PostedJob;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Model.PostedJob.ModelPostedJob;
import com.example.team32gb.jobit.View.PostedJob.ViewPostedJob;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PresenterPostedJob implements PresentInPostedJob{
    ViewPostedJob view;
    ModelPostedJob model;
    GreenRobotEventBus eventBus;

    public PresenterPostedJob(ViewPostedJob view) {
        this.view = view;
        model = new ModelPostedJob();
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
    public void getPost(String Uid) {
        model.getPost(Uid);
    }

    @Override
    @Subscribe
    public void showPost(List<ItemPostJob> itemPostJobs) {
        view.showPost(itemPostJobs);
    }
}
