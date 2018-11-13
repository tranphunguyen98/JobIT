package com.example.team32gb.jobit.View.WaitingAcceptNTD;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.PostedJob.ViewAdapterPosted;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingAcceptFragment extends Fragment {

    View v;
    private List<ItemPostJob> lsData;
    private RecyclerView recyclerView;
    public WaitingAcceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_waiting_accept,container,false);
        recyclerView = v.findViewById(R.id.rvListWaiting);

        ViewAdapterPosted adapter = new ViewAdapterPosted(getContext(),lsData);
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

}
