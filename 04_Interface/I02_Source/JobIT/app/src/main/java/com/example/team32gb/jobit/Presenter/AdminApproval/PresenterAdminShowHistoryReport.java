package com.example.team32gb.jobit.Presenter.AdminApproval;

import android.widget.TextView;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.Report.ReportHistoryModel;

import org.w3c.dom.Text;

public class PresenterAdminShowHistoryReport implements PresenterInAdminShowHistoryReport {
    ReportHistoryModel model = new ReportHistoryModel();
    EventBus eventBus;

    @Override
    public String getHistory(String uId, int typeUser, TextView textView) {
        return model.getHistoryReport(uId, typeUser, textView);
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
