package com.example.team32gb.jobit.View.WaitingForInterview;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.team32gb.jobit.Model.Applied.ItemJobApplied;
import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;
import com.example.team32gb.jobit.Presenter.Applied.PresenterApplied;
import com.example.team32gb.jobit.Presenter.Applied.PresenterInApplied;
import com.example.team32gb.jobit.Presenter.WaitingForInterview.PresenterInInterview;
import com.example.team32gb.jobit.Presenter.WaitingForInterview.PresenterInterview;
import com.example.team32gb.jobit.R;
import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.Applied.ListJobAppliedViewAdapter;
import com.example.team32gb.jobit.View.Applied.ViewListJobApplied;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class InterviewActivity extends AppCompatActivity implements ViewListJobInterview {

    private Toolbar myToolBar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private PresenterInInterview presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        myToolBar = findViewById(R.id.tbListInterview);
        recyclerView = this.findViewById(R.id.rvListInterview);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(Config.IS_ACTIVITY_APPLY,false);
        editor.apply();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myToolBar.setTitle("Chờ phỏng vấn");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        presenter = new PresenterInterview(this);
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
        ListJobAppliedViewAdapter adapter = new ListJobAppliedViewAdapter(this,itemJobApplieds);
        recyclerView.setAdapter(adapter);
    }
}