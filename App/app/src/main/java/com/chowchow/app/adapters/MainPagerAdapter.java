package com.chowchow.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackychan on 15/8/15.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        fragments.remove(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
