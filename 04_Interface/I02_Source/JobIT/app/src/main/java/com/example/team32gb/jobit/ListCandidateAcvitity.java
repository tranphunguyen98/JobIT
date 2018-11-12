package com.example.team32gb.jobit;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.team32gb.jobit.Utility.Util;

import java.util.List;

public class ListCandidateAcvitity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private TextView txtNameJob, txtTimeJob;
    private FrameLayout mainFrag;
    private BottomNavigationView mainNav;
    private String nameJob = "", timeJob = "",idCompany,idJob;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_candidate);


        myToolBar = findViewById(R.id.tbListCandidate);
        txtNameJob = this.findViewById(R.id.txtNameJob);
        txtTimeJob = this.findViewById(R.id.txtTime);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        if(bundle != null) {
            nameJob = bundle.getString("nameJob");
            timeJob = bundle.getString("timeJob");
            idCompany = bundle.getString("idCompany");
            idJob = bundle.getString("idJob");

            txtNameJob.setText(nameJob);
            Util.setSubTime(timeJob,txtTimeJob);
        }

        myToolBar.setTitle("");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mainFrag = findViewById(R.id.mainFrag);
        mainNav = findViewById(R.id.mainNav);
        CandidatePostedFragment candidatePostedFragment = new CandidatePostedFragment();
        candidatePostedFragment.showList(idCompany,idJob);
        setFragment(candidatePostedFragment);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              switch (menuItem.getItemId()){
                  case R.id.navPosted:
                      CandidatePostedFragment candidatePostedFragment = new CandidatePostedFragment();
                      candidatePostedFragment.showList(idCompany,idJob);
                      setFragment(candidatePostedFragment);
                      return true;
                  case R.id.navWaitingInterview:
                      WaitingInterviewFragment waitingInterviewFragment = new WaitingInterviewFragment();
                      waitingInterviewFragment.showList(idCompany,idJob);
                      setFragment(waitingInterviewFragment);
                      return true;
                  case R.id.navInviteJob:
                      setFragment(new InviteJobFragment());
                      return true;
                      default:
                          return false;
              }
           }
       });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detailjob_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrag,fragment);
        fragmentTransaction.commit();
    }
}
