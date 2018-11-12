package com.example.team32gb.jobit;

import android.graphics.Color;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class ListCandidateAppliedActivity extends AppCompatActivity {
    private Toolbar myToolBar;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private List<DataApplied> lsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_candidate_applied);

        recyclerView = findViewById(R.id.rvListCandidateApplied);
        myToolBar = findViewById(R.id.tbListCandidateApplied);
        myToolBar.setTitle("Danh sách các ứng viên đã nộp hồ sơ ");
        myToolBar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));
        myToolBar.setBackgroundColor(Color.parseColor("#FFD14D59"));
        setSupportActionBar(myToolBar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        init();
        ViewAdapterApplied adapterApplied = new ViewAdapterApplied(this, lsData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterApplied);
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
    public void init()
    {
        lsData = new ArrayList<>();
        lsData.add(new DataApplied("Nguyễn Văn A","5h trước"));
        lsData.add(new DataApplied("Nguyễn Văn B","1 tháng trước"));
        lsData.add(new DataApplied("Nguyễn Văn C","6 day trước"));
        lsData.add(new DataApplied("Nguyễn Văn D","45h trước"));
        lsData.add(new DataApplied("Nguyễn Văn E","35h trước"));
        lsData.add(new DataApplied("Nguyễn Văn F","15h trước"));
        lsData.add(new DataApplied("Nguyễn Văn G","25h trước"));
        lsData.add(new DataApplied("Nguyễn Văn H","4 ngày trước"));
        lsData.add(new DataApplied("Nguyễn Văn I","3h trước"));
        lsData.add(new DataApplied("Nguyễn Văn K","9h trước"));
        lsData.add(new DataApplied("Nguyễn Văn L","7h trước"));
        lsData.add(new DataApplied("Nguyễn Văn M","5 ngày trước"));
    }
}
