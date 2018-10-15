//package com.example.team32gb.jobit;
//
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//
//public class CreateMyCVActivity extends AppCompatActivity {
//    private ViewPager viewPager;
//    private TabLayout tabLayout;
//    public final static String TAG="CreateCV class";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_my_cv);
//
//        viewPager = this.findViewById(R.id.vpCreateCV);
//        tabLayout= this.findViewById(R.id.tlCreateCV);
//
//
//        MyFragmentPagerAdapter adapter= new MyFragmentPagerAdapter(this, getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//    }
//
//
//}
