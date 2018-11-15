package com.example.team32gb.jobit.Presenter.AdminStatistic;

import android.widget.TextView;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.AdminStatistic.AdminStatisticModel;

public class PresenterAdminStatistic implements PresenterInAdminStatistic {
    EventBus eventBus;
    AdminStatisticModel model;

    public PresenterAdminStatistic() {
        eventBus = GreenRobotEventBus.getInstance();
        model = new AdminStatisticModel();
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
    public long statisticEmployee(TextView tv) {
        return model.statisticEmployee(tv);
    }

    @Override
    public long statisticRecruiter(TextView tv) {
        return model.statisticRecruiter(tv);
    }
}
