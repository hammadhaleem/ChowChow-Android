package com.chowchow.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.chowchow.app.GCM_.logic.InstanceIdHelper;
import com.chowchow.app.adapters.MainPagerAdapter;
import com.chowchow.app.fragments.HistoryFragment;
import com.chowchow.app.fragments.SelectFriendsFragment;
import com.chowchow.app.fragments.SettingsFragment;
import com.chowchow.app.utils.GcmManager;
import com.chowchow.app.utils.InviteDialog;
import com.chowchow.app.utils.Constants;

import org.w3c.dom.Text;

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
        initViewPager();

        setupGCM();
    }

    private void setupGCM(){
        Intent launchIntent = getIntent();
        if ("gcm_test_app_notification_click_action".equals(launchIntent.getAction())) {
            Bundle data = launchIntent.getExtras();
            data.isEmpty();
            final String room_id = data.getString("test");
            Log.v("test",room_id);

            if (room_id.compareTo("0")!=0) {
                InviteDialog t = new InviteDialog(this,room_id);
                t.show();
                t.setName("hihi");
            }
        }
    }


    private void initViewPager() {
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
