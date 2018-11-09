package com.example.team32gb.jobit.View.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team32gb.jobit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminReportFragmentTab2 extends Fragment {
    RecyclerView rvEmployer;
    AdminReportAdapter adapter;

    public AdminReportFragmentTab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.admin_report_tab2, container, false);
        rvEmployer = v.findViewById(R.id.rvReportEmployer);
        adapter = new AdminReportAdapter();
        adapter.initDataTest();
        rvEmployer.setAdapter(adapter);
        rvEmployer.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

}
