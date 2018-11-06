package com.example.team32gb.jobit.Presenter.EmployerAppliedPosted;

import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.EmployerAppliedPosted.ModelEmployerAppliedPosted;
import com.example.team32gb.jobit.View.EmployerAppliedPosted.ViewEmployerAppliedPosted;

public class PresenterEmployerAppliedPosted implements PresenterInEmployerAppliedPosted {

    private ViewEmployerAppliedPosted view;
    private ModelEmployerAppliedPosted model;
    private GreenRobotEventBus eventBus;

    public PresenterEmployerAppliedPosted(ViewEmployerAppliedPosted view) {
        this.view = view;
        model = new ModelEmployerAppliedPosted();
        eventBus = GreenRobotEventBus.getInstance();
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
    public void getPost(String Uid) {

    }

    @Override
    public void Show(String strTime) {
        view.Show(strTime);
    }
}
