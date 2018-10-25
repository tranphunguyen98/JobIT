package com.example.team32gb.jobit.Presenter.ListJobSearch;

import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;

import java.util.List;

public interface PresenterInListJobSearch {
    void onCreate();
    void onDestroy();
    void getListJob();
    void showListJob(List<ItemJob> itemJobs);
}
