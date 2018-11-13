package com.example.team32gb.jobit.Presenter.ListJobSearch;

import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;

import java.util.List;

public interface PresenterInListJobSearch {
    void onCreate();
    void onDestroy();
    void getListJob(String timKiem, String diaDiem);
    void showListJob(List<ItemPostJob> ItemPostJob);
}
