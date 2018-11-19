package com.example.team32gb.jobit.Presenter.AdminApproval;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.Report.ReportHistoryModel;

public class PresenterAdminShowHistoryReport implements PresenterInAdminShowHistoryReport {
    ReportHistoryModel model = new ReportHistoryModel();
    EventBus eventBus;

    @Override
    public String getHistory(String uId, int typeUser) {
        return model.getHistoryReport(uId, typeUser);
    }

    @Override
    public void onCreate() {
        eventBus= GreenRobotEventBus.getInstance();
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }
}
