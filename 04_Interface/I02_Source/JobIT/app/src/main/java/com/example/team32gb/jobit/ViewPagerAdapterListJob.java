package com.example.team32gb.jobit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterListJob extends FragmentPagerAdapter {
    private List<Fragment> lsFrag = new ArrayList<>();
    private List<String> lsTitle = new ArrayList<>();

    public ViewPagerAdapterListJob(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lsFrag.get(i);
    }

    @Override
    public int getCount() {
        return lsTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lsTitle.get(position);
    }
    public void AddFragment(Fragment fragment, String title){
        lsFrag.add(fragment);
        lsTitle.add(title);
    }
}
