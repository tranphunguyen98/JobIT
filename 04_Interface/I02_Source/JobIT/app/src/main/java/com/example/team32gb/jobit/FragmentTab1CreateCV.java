package com.example.team32gb.jobit;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.team32gb.jobit.FragmentTab3CreateCV;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab1CreateCV extends Fragment {


    public FragmentTab1CreateCV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.tab1_create_cv, container, false);

        return v;
    }

}
