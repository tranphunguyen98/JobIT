package com.example.team32gb.jobit.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.team32gb.jobit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestDuyet extends AppCompatActivity implements View.OnClickListener {
    Button btnDuyet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_duyet);

        btnDuyet = findViewById(R.id.btnDuyet);
        btnDuyet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("applieds").child("tiDpKsiogPXaPmLpof4rd740PtQ2").child("jobId").child("-LPuUtZRHQWMAviltgDv");
        databaseReference.removeValue();
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("choPhongVans").child(FirebaseAuth.getInstance().getUid()).child("jobId");
        databaseReference1.setValue("-LPuUtZRHQWMAviltgDv");
    }
}
