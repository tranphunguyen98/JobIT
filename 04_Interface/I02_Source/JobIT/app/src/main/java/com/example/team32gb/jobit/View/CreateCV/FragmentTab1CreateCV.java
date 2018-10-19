package com.example.team32gb.jobit.View.CreateCV;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.team32gb.jobit.Model.JobSeekerProfile.UserModel;
import com.example.team32gb.jobit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab1CreateCV extends Fragment {
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private EditText edtEmail;
    public FragmentTab1CreateCV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.tab1_create_cv, container, false);
        edtEmail = v.findViewById(R.id.edtEmailTab1);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.e("kiemtra",dataSnapshot+ "");
//                DataSnapshot dataSnapshotUser = dataSnapshot.child("1fQjimh1qLTQIIZ2rUvim1ZRo483");
//                Log.e("kiemtra",dataSnapshotUser+ "");
//                UserModel userModel = dataSnapshotUser.getValue(UserModel.class);
//                //edtEmail.setText(userModel.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        return v;
    }

}
