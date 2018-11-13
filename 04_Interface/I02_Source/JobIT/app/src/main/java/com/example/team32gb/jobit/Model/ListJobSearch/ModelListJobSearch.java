package com.example.team32gb.jobit.Model.ListJobSearch;

import androidx.annotation.NonNull;

import android.util.Log;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.algolia.search.saas.RequestOptions;
import com.example.team32gb.jobit.Lib.GreenRobotEventBus;
import com.example.team32gb.jobit.Model.PostJob.DataPostJob;
import com.example.team32gb.jobit.Model.PostJob.ItemPostJob;
import com.example.team32gb.jobit.Model.PostJob.ModelPostJob;
import com.example.team32gb.jobit.Utility.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ModelListJobSearch {
    List<ItemPostJob> itemPostJobs;
    GreenRobotEventBus eventBus;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public ModelListJobSearch() {
        itemPostJobs = new ArrayList<>();
        eventBus = GreenRobotEventBus.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void getListJob(final String timKiem, String diaDiem) {
        Client client = new Client("OYGFHT3AFC", "a45f57a7c6cc7e6b49db961f46d39ca5");
        final Index index = client.getIndex("tinTuyenDungs");

        try {
            CompletionHandler completionHandler = new CompletionHandler() {
                @Override
                public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                    if(jsonObject == null && e != null) {
                        Log.e("kiemtraAlgolia", e.getMessage());
                    } else {
                        Log.e("kiemtraAlgolia", jsonObject.toString());
                        try {
                            JSONArray array = jsonObject.getJSONArray("hits");
                            Log.e("kiemtraAlgolia", array.length() + "");

                            if(array != null) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = (JSONObject) array.get(i);
                                    Log.e("kiemtraAlgolia", object.getString("nameJob") + "");
                                     itemPostJobs.add(Util.parserJSONToItemPost(object));
                                }
                                eventBus.post(itemPostJobs);
                            }



                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                }
            };
            JSONObject settings = new JSONObject().put("customRanking", "desc(maxSalary)");
            index.setSettingsAsync(settings, completionHandler);
            Query query = new Query(timKiem)
                    .setAttributesToHighlight("nameJob")
                    .setHitsPerPage(20);
            index.searchAsync(query, completionHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                DataSnapshot dsTin = dataSnapshot.child("tinTuyenDungs");
//                for (DataSnapshot snapshot : dsTin.getChildren()) {
//                    for (DataSnapshot snapshotChild : snapshot.getChildren()) {
//                        DataPostJob dataPostJob = snapshotChild.getValue(DataPostJob.class);
//                        ItemPostJob itemPostJob = new ItemPostJob();
//                        itemPostJob.setDataPostJob(dataPostJob);
//                        itemPostJob.setIdJob(snapshotChild.getKey());
//                        itemPostJob.setIdCompany(snapshot.getKey());
//                        Log.e("kiemtraname", snapshot.getKey());
//                        String nameCompany = dataSnapshot.child("companys").child(itemPostJob.getIdCompany()).child("name").getValue(String.class);
//
//                        itemPostJob.setNameCompany(nameCompany);
//
//                        itemPostJobs.add(itemPostJob);
//                    }
//                }
//                eventBus.post(itemPostJobs);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

}
