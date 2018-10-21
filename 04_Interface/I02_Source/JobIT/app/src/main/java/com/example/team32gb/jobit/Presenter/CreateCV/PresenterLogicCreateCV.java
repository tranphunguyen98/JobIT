package com.example.team32gb.jobit.Presenter.CreateCV;

import com.example.team32gb.jobit.Model.CreateCV.CVEmployeeModel;
import com.example.team32gb.jobit.Model.CreateCV.ModelCreateCV;
import com.example.team32gb.jobit.Model.CreateCV.ProjectInCVModel;
import com.example.team32gb.jobit.View.CreateCV.ViewCreateCV;

import java.util.List;

public class PresenterLogicCreateCV implements PresenterInCreateCV{
    private ModelCreateCV modelCreateCV;
    private ViewCreateCV viewCreateCV;
    public PresenterLogicCreateCV(ViewCreateCV viewCreateCV){
        this.viewCreateCV = viewCreateCV;
        modelCreateCV = new ModelCreateCV();
    }

    @Override
    public void saveCV(String uid, CVEmployeeModel cvEmployeeModel, List<ProjectInCVModel> projectInCVModels) {
            modelCreateCV.saveCV(uid,cvEmployeeModel,projectInCVModels);
    }
}
