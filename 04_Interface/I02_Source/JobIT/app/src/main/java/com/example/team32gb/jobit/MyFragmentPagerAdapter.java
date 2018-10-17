//package com.example.team32gb.jobit;
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
//    private final static int TAB1_PERSONAL_INFO = 0;
//    private final static int TAB2_JOB_SKILL = 1;
//    private final static int TAB3_PROJECT = 2;
//    private final static int TAB_AMOUNT = 3;
//
//    private Context mContext;
//
//    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
//        super(fm);
//        this.mContext = context;
//    }
//
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case TAB1_PERSONAL_INFO:
//                return new FragmentTab1CreateCV();
//            case TAB2_JOB_SKILL:
//                return new FragmentTab2CreateCV();
//            case TAB3_PROJECT:
//                return new FragmentTab3CreateCV();
//            default:
//                return new FragmentTab1CreateCV();
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return TAB_AMOUNT;
//    }
//
//    //get title for each tab
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case TAB1_PERSONAL_INFO:
//                return "Thông tin cá nhân";
//            case TAB2_JOB_SKILL:
//                return "Kỹ năng nghề nghiệp";
//            case TAB3_PROJECT:
//                return "Project";
//            default:
//                return null;
//        }
//    }
//}

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final static int TAB1_PERSONAL_INFO = 0;
    private final static int TAB2_JOB_SKILL = 1;
    private final static int TAB3_PROJECT = 2;
    private final static int TAB_AMOUNT = 3;

    private Context mContext;

    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB1_PERSONAL_INFO:
                return new FragmentTab1CreateCV();
            case TAB2_JOB_SKILL:
                return new FragmentTab2CreateCV();
            case TAB3_PROJECT:
                return new FragmentTab3CreateCV();
            default:
                return new FragmentTab1CreateCV();
        }
    }

    @Override
    public int getCount() {
        return TAB_AMOUNT;
    }

    //get title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB1_PERSONAL_INFO:
                return "Thông tin cá nhân";
            case TAB2_JOB_SKILL:
                return "Kỹ năng nghề nghiệp";
            case TAB3_PROJECT:
                return "Project";
            default:
                return null;
        }
    }
}
