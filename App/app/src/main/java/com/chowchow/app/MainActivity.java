package com.chowchow.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.chowchow.app.adapters.MainPagerAdapter;
import com.chowchow.app.fragments.HistoryFragment;
import com.chowchow.app.fragments.SelectFriendsFragment;
import com.chowchow.app.fragments.SettingsFragment;
import com.chowchow.app.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.activity_main_view_pager)
    ViewPager viewPager;

    FragmentManager fragmentManager;
    MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        setupViewPager();
    }

    private void setupViewPager() {
        fragmentManager = getSupportFragmentManager();
        pagerAdapter = new MainPagerAdapter(fragmentManager);

        SelectFriendsFragment selectFriendsFragment = new SelectFriendsFragment();
        pagerAdapter.addFragment(selectFriendsFragment);
        HistoryFragment historyFragment = new HistoryFragment();
        pagerAdapter.addFragment(historyFragment);
        SettingsFragment settingsFragment = new SettingsFragment();
        pagerAdapter.addFragment(settingsFragment);


        viewPager.setAdapter(pagerAdapter);

    }

    public void navigateToFragment(Constants.MainFragment fragment) {
        Log.d(TAG, "navigateToFragment(), navigating to fragment == " + fragment.toString());
        viewPager.setCurrentItem(fragment.getValue());
    }

    public static String getTAG() {
        return TAG;
    }
}
