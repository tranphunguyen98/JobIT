package com.example.team32gb.jobit.Presenter.CreateCV;

import com.example.team32gb.jobit.Model.CreateCV.CVEmployeeModel;
import com.example.team32gb.jobit.Model.CreateCV.ProjectInCVModel;

import java.util.List;

public interface PresenterInCreateCV {
    public void saveCV(String uid, CVEmployeeModel cvEmployeeModel, List<ProjectInCVModel> projectInCVModels);
}
