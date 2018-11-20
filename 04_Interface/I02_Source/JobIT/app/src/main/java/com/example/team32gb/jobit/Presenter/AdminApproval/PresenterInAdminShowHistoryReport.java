package com.example.team32gb.jobit.Presenter.AdminApproval;

import android.widget.TextView;

interface PresenterInAdminShowHistoryReport {
    String getHistory(String uId, int typeUser, TextView textView);
    void onCreate();
    void onDestroy();
}
