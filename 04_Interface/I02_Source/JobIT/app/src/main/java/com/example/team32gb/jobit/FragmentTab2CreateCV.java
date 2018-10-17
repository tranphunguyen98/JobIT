package com.example.team32gb.jobit;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab2CreateCV extends Fragment {


    public FragmentTab2CreateCV() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2_create_cv, container, false);
        Log.e(FragmentTab3CreateCV.tag, "mở tab 2");
        return v;
    }

}
