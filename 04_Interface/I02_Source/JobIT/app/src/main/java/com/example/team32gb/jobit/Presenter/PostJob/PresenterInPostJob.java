
package com.example.team32gb.jobit.Presenter.PostJob;

import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import java.util.List;

public interface PresenterInPostJob {
    void SavePost(String Uid, DataPostJob dataPostJob);
    void SavePostEdit(String idJob,String Uid, DataPostJob dataPostJob);
}
