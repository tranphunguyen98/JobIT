package com.example.team32gb.jobit.View.ListJobSearch;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Model.ListJobSearch.ItemJob;
import com.example.team32gb.jobit.Presenter.ListJobSearch.PresenterInListJobSearch;
import com.example.team32gb.jobit.Presenter.ListJobSearch.PresenterLogicListJobSearch;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.View.ListJob.ListJobViewAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListJobSearchActivity extends AppCompatActivity implements ViewListJobSearch {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private List<DataJob> lsData;
    private PresenterInListJobSearch presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_job_search);
        myToolBar = findViewById(R.id.tbListJobSearch);
        recyclerView = this.findViewById(R.id.rvListJobSearch);
//        i/nitData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myToolBar.setTitle("Tìm việc");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        presenter = new PresenterLogicListJobSearch(this);
        presenter.onCreate();
        presenter.getListJob();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listjob_actionbar, menu);
        MenuItem searchItem = (MenuItem) menu.findItem(R.id.tbSearch).getActionView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void initData() {
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

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("tinTuyenDungs").child("jTdHn8sjWxSwkvIJKZHZvHsb2sa2");
        String key1 = databaseReference.push().getKey();
        DatabaseReference df1 = databaseReference.child(key1);
        df1.setValue(lsData.get(0));
        String key2 = databaseReference.push().getKey();
        DatabaseReference df2 = databaseReference.child(key2);
        df2.setValue(lsData.get(1));
    }

    @Override
    public void showListJob(List<ItemJob> itemJobs) {
        Log.e("kiemtrasnap1",itemJobs.get(0).getNameJob());
        ListJobViewAdapter adapter = new ListJobViewAdapter(this,itemJobs);
        recyclerView.setAdapter(adapter);

    }
}
