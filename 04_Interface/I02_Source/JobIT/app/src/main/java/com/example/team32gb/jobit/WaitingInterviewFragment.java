package com.example.team32gb.jobit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team32gb.jobit.Utility.FragmentCallBack;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingInterviewFragment extends Fragment implements FragmentCallBack {

    View v;
    private RecyclerView recyclerView;
    private List<DataApplied> lsData;
    public WaitingInterviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_waiting_interview,container,false);
        recyclerView = v.findViewById(R.id.rvWaitingInterview);
        ViewAdapterApplied adapter = new ViewAdapterApplied(getContext(),lsData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lsData = new ArrayList<>();
        lsData.add(new DataApplied("Nguyễn Văn A","5h trước"));
        lsData.add(new DataApplied("Nguyễn Văn B","1 tháng trước"));
        lsData.add(new DataApplied("Nguyễn Văn C","6 day trước"));
        lsData.add(new DataApplied("Nguyễn Văn D","45h trước"));
        lsData.add(new DataApplied("Nguyễn Văn E","35h trước"));
        lsData.add(new DataApplied("Nguyễn Văn F","15h trước"));
        lsData.add(new DataApplied("Nguyễn Văn G","25h trước"));
        lsData.add(new DataApplied("Nguyễn Văn H","4 ngày trước"));
        lsData.add(new DataApplied("Nguyễn Văn I","3h trước"));
        lsData.add(new DataApplied("Nguyễn Văn K","9h trước"));
        lsData.add(new DataApplied("Nguyễn Văn L","7h trước"));
        lsData.add(new DataApplied("Nguyễn Văn M","5 ngày trước"));
    }

    @Override
    public void showList(String idCompany, String idJob) {

    }
}
