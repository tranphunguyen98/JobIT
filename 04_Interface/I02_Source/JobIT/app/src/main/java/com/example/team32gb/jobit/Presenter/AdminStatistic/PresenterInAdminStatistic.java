package com.example.team32gb.jobit.Presenter.AdminStatistic;

import android.app.ProgressDialog;
import android.widget.TextView;

interface PresenterInAdminStatistic {
    public void onCreate();
    public void onDestroy();
    public long statisticEmployee(TextView tv, ProgressDialog dialog);
    public long statisticRecruiter(TextView tv, ProgressDialog dialog);
}
