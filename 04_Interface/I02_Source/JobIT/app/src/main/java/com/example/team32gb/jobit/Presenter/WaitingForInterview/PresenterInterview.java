package com.example.team32gb.jobit.Presenter.WaitingForInterview;

import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;
import com.example.team32gb.jobit.Model.Applied.ModelApplied;
import com.example.team32gb.jobit.Model.WaitingForInterview.ModelInterview;
import com.example.team32gb.jobit.Presenter.Applied.PresenterInApplied;
import com.example.team32gb.jobit.View.Applied.ViewListJobApplied;
import com.example.team32gb.jobit.View.WaitingForInterview.ViewListJobInterview;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PresenterInterview implements PresenterInInterview {
    ViewListJobInterview view;
    ModelInterview model;
    GreenRobotEventBus eventBus;

    public PresenterInterview(ViewListJobInterview view){
        this.view = view;
        model = new ModelInterview();
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
    public void showListJob(List<ItemJobApplied> itemJobApplieds) {
        Log.e("kiemtraapplied",itemJobApplieds.get(0).getNameJob());
        view.showListJob(itemJobApplieds);
    }

}
