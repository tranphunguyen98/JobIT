package com.example.team32gb.jobit.Presenter.PostJob;

import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Model.PostJob.ModelPostJob;
import com.example.team32gb.jobit.View.PostJob.ViewPostJob;

public class PresenterPostJob implements PresenterInPostJob {
    ViewPostJob viewPostJob;
    ModelPostJob modelPostJob;
    public PresenterPostJob(ViewPostJob viewPostJob) {
        this.viewPostJob = viewPostJob;
        modelPostJob = new ModelPostJob();
    }

    @Override
    public void SavePost(String Uid, DataPostJob dataPostJob) {
        modelPostJob.SavePost(Uid,dataPostJob);
    }
}
