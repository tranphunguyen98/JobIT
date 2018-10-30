package com.example.team32gb.jobit.Presenter.SignUpAccountBusiness;

import com.example.team32gb.jobit.Lib.EventBus;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.SignUpAccountBusiness.InfoCompanyModel;
import com.example.team32gb.jobit.Model.SignUpAccountBusiness.ModelSignUpAccountBusiness;
import com.example.team32gb.jobit.View.SignUpAccountBusiness.ViewSignUpAccountBusiness;

import org.greenrobot.eventbus.Subscribe;

public class PresenterLogicSignUpAccountBusiness implements PresenterInSignUpAccountBusiness {
    private ViewSignUpAccountBusiness view;
    private ModelSignUpAccountBusiness model;
    private EventBus eventBus;

    public PresenterLogicSignUpAccountBusiness(ViewSignUpAccountBusiness view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        model = new ModelSignUpAccountBusiness();
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
    public void saveInfoCompany(String uid,InfoCompanyModel infoCompanyModel) {
        model.saveInfoComanyToServer(uid,infoCompanyModel);
    }

    @Override
    public void getInfoCompany(String uid) {
        model.getInfoCompanyFromUid(uid);
    }

    @Override
    @Subscribe
    public void showInfoCompany(InfoCompanyModel model) {
        view.showInfoCompany(model);
    }

}
