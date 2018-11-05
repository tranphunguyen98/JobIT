package com.example.team32gb.jobit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.team32gb.jobit.Utility.Config;
import com.example.team32gb.jobit.View.PostedJob.PostedFragment;

public class JobRecruitmentActivity extends AppCompatActivity {
  private Toolbar myToolBar;
    private ActionBar actionBar;
    private FrameLayout mainFrag;
    private BottomNavigationView mainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_recruitment);

        mainFrag = findViewById(R.id.mainFrag);
        mainNav = findViewById(R.id.mainNav);

        myToolBar = findViewById(R.id.tbJobRecruitment);
        myToolBar.setTitle("Danh sách việc làm ");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setFragment(new PostedFragment());

        switch (Config.CHECK_FRAV){
            case 0:
                setFragment(new PostedFragment());
                break;
            case 1:
                setFragment(new WaitingAcceptFragment());
                break;
            case 2:
                setFragment(new ExpireFragment());
                break;
            case 3:
                setFragment(new UpLoadPostFragment());
                break;
        }
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navPosted:
                        setFragment(new PostedFragment());
                        return true;
                    case R.id.navWaiting:
                        setFragment(new WaitingAcceptFragment());
                        return true;
                    case R.id.navExpire:
                        setFragment(new ExpireFragment());
                        return true;
                    case R.id.navUploadPost:
                        setFragment(new UpLoadPostFragment());
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
