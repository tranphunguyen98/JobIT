package com.example.team32gb.jobit.Presenter.AdminStatistic;

import android.widget.TextView;

interface PresenterInAdminStatistic {
    public void onCreate();
    public void onDestroy();
    public long statisticEmployee(TextView tv);
    public long statisticRecruiter(TextView tv);
}
