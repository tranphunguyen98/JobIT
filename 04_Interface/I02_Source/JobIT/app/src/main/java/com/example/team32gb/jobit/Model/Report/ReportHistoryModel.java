package com.example.team32gb.jobit.Model.Report;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

import static com.example.team32gb.jobit.Utility.Config.IS_JOB_SEEKER;
import static com.example.team32gb.jobit.Utility.Config.IS_RECRUITER;
import static com.example.team32gb.jobit.Utility.Config.REF_JOBSEEKERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_RECRUITERS_NODE;
import static com.example.team32gb.jobit.Utility.Config.REF_REPORT;

public class ReportHistoryModel {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(REF_REPORT);


    public String getHistoryReport(String uId, int typeUser){
        final StringBuilder[] stringBuilder = new StringBuilder[1];
        stringBuilder[0]= new StringBuilder();
        if (typeUser==IS_JOB_SEEKER){
            ref = ref.child(REF_JOBSEEKERS_NODE).child(uId);
        }
        else if (typeUser==IS_RECRUITER){
            ref = ref.child(REF_RECRUITERS_NODE).child(uId);
        }
        else{
            return null;
        }

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() ==0){
                    stringBuilder[0].append("Lịch sử tố cáo trống");
                }
                else{
                     int count =0;
                    for(DataSnapshot dataChild : dataSnapshot.getChildren()){
                        count++;
                        ReportModel model = dataChild.getValue(ReportModel.class);
                        if (model!=null){
                            stringBuilder[0].append("LẦN ");
                            stringBuilder[0].append(count);
                            stringBuilder[0].append("\n\tThời gian bị tố cáo: ");
                            stringBuilder[0].append(model.getDateSendReport());
                            stringBuilder[0].append("\n\tTình trạng: ");
                            if (model.getisWarned()){
                                stringBuilder[0].append("Đã bị cảnh cáo\n");
                            }
                            else{
                                stringBuilder[0].append("Chưa bị cảnh cáo\n");
                            }
                            stringBuilder[0].append("\tMô tả từ người tố cáo: ") ;
                            stringBuilder[0].append(model.getDecription());
                            stringBuilder[0].append("\n");
                        }
                        else{
                            stringBuilder[0].append("Không thể tìm thấy lịch sử tố cáo");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    stringBuilder[0].append("Không thể tìm thấy lịch sử tố cáo");
            }
        });
        return stringBuilder.toString();
    }


}
