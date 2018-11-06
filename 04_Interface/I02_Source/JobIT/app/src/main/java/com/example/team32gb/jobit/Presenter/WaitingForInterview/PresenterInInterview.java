package com.example.team32gb.jobit.Presenter.WaitingForInterview;

import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;

import java.util.List;

public interface PresenterInInterview {
    void onCreate();
    void onDestroy();
    void getListJob(String uid);
    void showListJob(List<ItemJobApplied> itemJobApplieds);
}
