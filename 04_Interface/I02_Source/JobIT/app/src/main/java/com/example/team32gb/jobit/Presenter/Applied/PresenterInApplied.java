package com.example.team32gb.jobit.Presenter.Applied;

import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;
import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;

import java.util.List;

public interface PresenterInApplied {
    void onCreate();
    void onDestroy();
    void getListJob(String uid);
    void showListJob(List<ItemJobApplied> itemJobApplieds);
}
