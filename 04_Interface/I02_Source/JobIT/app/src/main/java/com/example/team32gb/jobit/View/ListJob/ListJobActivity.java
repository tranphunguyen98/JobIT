package com.example.team32gb.jobit.View.ListJob;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.team32gb.jobit.R;

public class ListJobActivity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapterListJob adapterListJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listjob);

        myToolBar = findViewById(R.id.tbListJob);
        myToolBar.setTitle("Tìm việc");
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tabListJob);
        viewPager = findViewById(R.id.vpListJob);
        adapterListJob = new ViewPagerAdapterListJob(getSupportFragmentManager());

//        adapterListJob.AddFragment(new FragmentAllJob(),"Tất cả");
//        adapterListJob.AddFragment(new FragmentNewJob(),"Việc mới");
        adapterListJob.AddFragment(new FragmentTaoCV(),"Tạo CV");
        adapterListJob.AddFragment(new FragmentMore(),"More");
        viewPager.setAdapter(adapterListJob);
        tabLayout.setupWithViewPager(viewPager);

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
}
