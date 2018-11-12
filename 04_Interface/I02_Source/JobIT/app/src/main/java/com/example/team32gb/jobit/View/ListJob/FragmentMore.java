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

public class FragmentMore extends Fragment implements View.OnClickListener {
    View v;
    private Button btnSignIn, btnMyJob, btnApplied;
    public FragmentMore() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.more_listjob_fragment,container,false);
        btnSignIn = v.findViewById(R.id.btnSignIn_Register);
        btnMyJob = v.findViewById(R.id.btnMyJob);
        btnApplied = v.findViewById(R.id.btnApplied);

        btnSignIn.setOnClickListener(this);
        btnMyJob.setOnClickListener(this);
        btnApplied.setOnClickListener(this);
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"Test button",Toast.LENGTH_LONG).show();
    }
}
