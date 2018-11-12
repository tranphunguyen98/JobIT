package com.example.team32gb.jobit;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.Utility.FragmentCallBack;
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
public class CandidatePostedFragment extends Fragment implements FragmentCallBack {

    View v;
    private RecyclerView recyclerView;
    private List<DataApplied> lsData;
    public CandidatePostedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_candidate_posted,container,false);
        recyclerView = v.findViewById(R.id.rvListCandidate);


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
                DataSnapshot dsJob =dataSnapshot.child("choDuyets").child(idCompany).child(idJob);
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

                ViewAdapterApplied adapter = new ViewAdapterApplied(getContext(),lsData);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
