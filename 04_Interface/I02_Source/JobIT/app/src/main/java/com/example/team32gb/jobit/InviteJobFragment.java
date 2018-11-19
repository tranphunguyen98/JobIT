package com.example.team32gb.jobit;


import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team32gb.jobit.Adapter.*;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.Utility.FragmentCallBack;
import com.example.team32gb.jobit.View.InviteJob.InviteJobActivity;
import com.example.team32gb.jobit.View.WaitingAcceptNTD.ViewAdapterApplied;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InviteJobFragment extends Fragment implements FragmentCallBack {

    View v;
    private RecyclerView recyclerView;
    private List<DataApplied> lsData;
    public InviteJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_invite_job,container,false);
        recyclerView = v.findViewById(R.id.rvInviteJob);
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

    }
    @Override
    public void showList(final String idCompany, final String idJob) {
        Log.e("kiemtratruyen",idCompany + idJob );
        DatabaseReference nodeRoot = FirebaseDatabase.getInstance().getReference();
        nodeRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dsJob =dataSnapshot.child("moiLamNTDs").child(idCompany).child(idJob);
                for(DataSnapshot dsCandidate : dsJob.getChildren()) {
                    DataApplied dataApplied = new DataApplied();
                    String idCadidate = dsCandidate.getKey();
                    String time = dsCandidate.child("timeApplied").getValue(String.class);
                    String name = dataSnapshot.child(Config.REF_JOBSEEKERS_NODE).child(idCadidate).child("name").getValue(String.class);

                    Log.e("kiemtra11",time + ":" + name);
                    dataApplied.setIdJobSeeker(idCadidate);
                    dataApplied.setName(name);
                    dataApplied.setDayApplied(time);
                    dataApplied.setIdJob(idJob);
                    dataApplied.setIdCompany(idCompany);
                    lsData.add(dataApplied);
                }

                final ViewAdapterInvite adapter = new ViewAdapterInvite(getContext(),lsData);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                SwipeControllerActions swipeControllerActions = new SwipeControllerActions() {
                    @Override
                    public void onLeftClicked(int position) {
                        super.onLeftClicked(position);
                        Log.e("kiemtraSwipe","Left" + position);

                    }

                    @Override
                    public void onRightClicked(int position) {
                        super.onRightClicked(position);
                        Log.e("kiemtraSwipe","Right" + position);
                    }
                };


                recyclerView.setLayoutManager(layoutManager);


                recyclerView.setAdapter(adapter);

                final SwipeController swipeController = new SwipeController(swipeControllerActions);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);

                itemTouchHelper.attachToRecyclerView(recyclerView);

                ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
                itemTouchhelper.attachToRecyclerView(recyclerView);

                recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                        swipeController.onDraw(c);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupRecyclerView() {

    }
}
