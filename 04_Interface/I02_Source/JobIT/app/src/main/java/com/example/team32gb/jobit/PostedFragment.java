package com.example.team32gb.jobit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostedFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<DataJob> lsData;
    public PostedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_posted,container,false);
        recyclerView = v.findViewById(R.id.rvListPosted);

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
        lsData.add(new DataJob("Thực tập","ABC","3h"));
        lsData.add(new DataJob("Thực tập 1","ABCD","5h"));
        lsData.add(new DataJob("Thực tập 2","ABC","3h"));
        lsData.add(new DataJob("Thực tập 3","ABC","3h"));
        lsData.add(new DataJob("Thực tập 4","ABC","3h"));
        lsData.add(new DataJob("Thực tập 5","ABC","3h"));
        lsData.add(new DataJob("Thực tập 6","ABC","3h"));
        lsData.add(new DataJob("Thực tập 7","ABC","3h"));
        lsData.add(new DataJob("Thực tập 8","ABC","3h"));
        lsData.add(new DataJob("Thực tập 9","ABC","3h"));
        lsData.add(new DataJob("Thực tập 10","ABC","3h"));
        lsData.add(new DataJob("Thực tập 11","ABC","3h"));
        lsData.add(new DataJob("Thực tập 12","ABC","3h"));
        lsData.add(new DataJob("Thực tập 13","ABC","3h"));
        lsData.add(new DataJob("Thực tập 14","ABC","3h"));
        lsData.add(new DataJob("Thực tập 15","ABC","3h"));
        lsData.add(new DataJob("Thực tập 16","ABC","3h"));
        lsData.add(new DataJob("Thực tập 17","ABC","3h"));
        lsData.add(new DataJob("Thực tập 18","ABC","3h"));
        lsData.add(new DataJob("Thực tập 19","ABC","3h"));
    }
}
