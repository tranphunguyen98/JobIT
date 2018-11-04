package com.example.team32gb.jobit.Presenter.Applied;

import android.util.Log;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;
import com.example.team32gb.jobit.Model.Applied.ModelApplied;
import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;
import com.example.team32gb.jobit.Model.ListJobSearch.ModelListJobSearch;
import com.example.team32gb.jobit.Presenter.ListJobSearch.PresenterInListJobSearch;
import com.example.team32gb.jobit.View.Applied.ViewListJobApplied;
import com.example.team32gb.jobit.View.ListJobSearch.ViewListJobSearch;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class PresenterApplied implements PresenterInApplied {
    ViewListJobApplied view;
    ModelApplied model;
    GreenRobotEventBus eventBus;

    public PresenterApplied(ViewListJobApplied view){
        this.view = view;
        model = new ModelApplied();
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
