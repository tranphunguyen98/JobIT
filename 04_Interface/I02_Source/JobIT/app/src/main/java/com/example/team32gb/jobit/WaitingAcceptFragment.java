package com.example.team32gb.jobit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingAcceptFragment extends Fragment {

    View v;
    private List<DataJob> lsData;
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
        lsData.add(new DataJob("Thực tập sjdfdsfsd dsfsd hjahsdjf","ABC","3h"));
        lsData.add(new DataJob("Thực tập dsasdfasd dfasd fsd 1","ABCD","5h"));
        lsData.add(new DataJob("Thực tập adfaadfasdf sdfa 2","ABC","3h"));
        lsData.add(new DataJob("Thực tadfasdf adfsda ập 3","AgfgBC","3h"));
        lsData.add(new DataJob("Thực tadfasdfas aập 4","ABC","3h"));
        lsData.add(new DataJob("Thực adfasd atập 5","ABffgfgC","3h"));
        lsData.add(new DataJob("Thực tậsáadfasdfs p 6","ABC","3h"));
        lsData.add(new DataJob("Thực tập 7","ABC","3h"));
        lsData.add(new DataJob("Thực tậdfsfap 8","ABssdC","3h"));
        lsData.add(new DataJob("Thực tậápsadfsadfsd 9","ABC","3h"));
        lsData.add(new DataJob("Thực tập 10","AdgfBC","3h"));
        lsData.add(new DataJob("Thực tậsadfsdfp 11","ABC","3h"));
        lsData.add(new DataJob("Thực tập 12","ABC","3h"));
        lsData.add(new DataJob("Thực tập 13","ABC","3h"));
        lsData.add(new DataJob("Thực tậsadfsdfp 14","ABC","3h"));
        lsData.add(new DataJob("Thực tập 15","ABC","3h"));
        lsData.add(new DataJob("Thực tsdfsdập 16","ABrrete","3h"));
        lsData.add(new DataJob("Thực tập 17","ABC","3h"));
        lsData.add(new DataJob("Thực tsdfsập 18","ABC","3h"));
        lsData.add(new DataJob("Thực tập 19","ABC","3h"));
    }

}
