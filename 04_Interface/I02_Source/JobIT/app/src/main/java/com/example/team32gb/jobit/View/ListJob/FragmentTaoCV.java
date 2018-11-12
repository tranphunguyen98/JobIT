package com.example.team32gb.jobit.View.ListJob;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.team32gb.jobit.R;

public class FragmentTaoCV extends Fragment implements View.OnClickListener {
    View v;
    private Button btnTaoCV;

    public FragmentTaoCV() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.taocv_listjob_fragment,container,false);
        btnTaoCV = v.findViewById(R.id.btnTaoCV);
        btnTaoCV.setOnClickListener(this);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Tao Cv của tôi", Toast.LENGTH_LONG).show();
    }

}
