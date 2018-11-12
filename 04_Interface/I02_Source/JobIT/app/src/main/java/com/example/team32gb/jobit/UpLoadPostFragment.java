package com.example.team32gb.jobit;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.team32gb.jobit.View.PostJob.PostJobRecruitmentActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpLoadPostFragment extends Fragment {

    View v;
    private Button btnUpLoad;
    public UpLoadPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_up_load_post,container,false);
        btnUpLoad = v.findViewById(R.id.btnUploadPost);
        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thay cái activity vào chỗ này nữa nhan, tui để HomeRecruitmentActivity để test thôi
                Intent intent = new Intent(getContext().getApplicationContext(),PostJobRecruitmentActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
