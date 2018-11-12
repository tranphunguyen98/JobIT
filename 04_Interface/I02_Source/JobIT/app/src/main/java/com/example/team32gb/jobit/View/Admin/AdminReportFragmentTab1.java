package com.example.team32gb.jobit.View.Admin;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.team32gb.jobit.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminReportFragmentTab1 extends Fragment {
    private RecyclerView rvReportEmployee;
    List<AdminReportModel> arrReport= new ArrayList<>();
    AdminReportAdapter adapter;
    Context context;

    public AdminReportFragmentTab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.admin_report_tab1_, container, false);
        rvReportEmployee= v.findViewById(R.id.rvReportEmployee);

        adapter= new AdminReportAdapter(arrReport);
        //todo: test
        adapter.initDataTest();

        rvReportEmployee.setAdapter(adapter);

        rvReportEmployee.setLayoutManager(new LinearLayoutManager(getActivity()));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_recyclerview_admin);

        rvReportEmployee.setLayoutAnimation(animationController);
        runLayoutAnimation(rvReportEmployee);

        return v;
    }

    private  void runLayoutAnimation(final RecyclerView recyclerView){
        final Context context= recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_recyclerview_admin);

        recyclerView.setLayoutAnimation(controller);

        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


}
