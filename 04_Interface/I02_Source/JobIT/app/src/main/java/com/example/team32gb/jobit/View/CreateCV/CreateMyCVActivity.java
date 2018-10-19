package com.example.team32gb.jobit.View.CreateCV;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.team32gb.jobit.R;

public class CreateMyCVActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    public final static String TAG = "CreateCV class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_my_cv);

        viewPager = this.findViewById(R.id.vpCreateCV);
        tabLayout = this.findViewById(R.id.tlCreateCV);
        toolbar = this.findViewById(R.id.tb_createCv);
        setSupportActionBar(toolbar);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_cv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itHomeCreateCV:
                Toast.makeText(this, "Đã lưu", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
