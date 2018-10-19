package com.example.team32gb.jobit;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class PostedActivity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private List<DataJob> lsData;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);

        myToolBar = findViewById(R.id.tbPosted);
        myToolBar.setTitle("Các tin đã đăng");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rvListPosted);
        init();
        ViewAdapterPosted adapter = new ViewAdapterPosted(this, lsData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    public void init(){
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
        lsData.add(new DataJob("Thực tập 10","ABC","3h"));
        lsData.add(new DataJob("Thực tập 11","ABC","3h"));
        lsData.add(new DataJob("Thực tập 12","ABC","3h"));
        lsData.add(new DataJob("Thực tập 13","ABC","3h"));
        lsData.add(new DataJob("Thực tập 14","ABC","3h"));
        lsData.add(new DataJob("Thực tập 15","ABC","3h"));
        lsData.add(new DataJob("Thực tập 16","ABC","3h"));
        lsData.add(new DataJob("Thực tập 17","ABC","3h"));
        lsData.add(new DataJob("Thực tập 18","ABC","3h"));
        lsData.add(new DataJob("Thực tập 19","ABC","3h"));
    }
}
