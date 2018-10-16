package com.example.team32gb.jobit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentNewJob extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<DataJob> lsData;
    public FragmentNewJob() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.newjob_fragment,container, false);
        recyclerView = v.findViewById(R.id.rvNewJob);
        ListJobViewAdapter adapter = new ListJobViewAdapter(getContext(),lsData);
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
    }
}
