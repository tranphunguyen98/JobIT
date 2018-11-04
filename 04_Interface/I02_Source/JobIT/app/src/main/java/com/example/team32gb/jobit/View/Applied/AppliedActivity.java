package com.example.team32gb.jobit.View.Applied;

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

import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;
import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Presenter.Applied.PresenterApplied;
import com.example.team32gb.jobit.Presenter.Applied.PresenterInApplied;
import com.example.team32gb.jobit.Presenter.ListJobSearch.PresenterInListJobSearch;
import com.example.team32gb.jobit.Presenter.ListJobSearch.PresenterLogicListJobSearch;
import com.example.team32gb.jobit.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AppliedActivity extends AppCompatActivity implements ViewListJobApplied {

    private Toolbar myToolBar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private List<DataJob> lsData;
    private PresenterInApplied presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied);
        myToolBar = findViewById(R.id.tbListApplied);
        recyclerView = this.findViewById(R.id.rvListApplied);
//        i/nitData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myToolBar.setTitle("Đã apply");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        presenter = new PresenterApplied(this);
        presenter.onCreate();
        presenter.getListJob(FirebaseAuth.getInstance().getUid());

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

    @Override
    public void showListJob(List<ItemJobApplied> itemJobApplieds) {
        Log.e("kiemtraItem",itemJobApplieds.size() + "");
        ListJobAppliedViewAdapter adapter = new ListJobAppliedViewAdapter(this,itemJobApplieds);
        recyclerView.setAdapter(adapter);
    }
}
