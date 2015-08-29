package com.chowchow.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.chowchow.app.GCM_.logic.InstanceIdHelper;
import com.chowchow.app.adapters.MainPagerAdapter;
import com.chowchow.app.fragments.HistoryFragment;
import com.chowchow.app.fragments.SelectFriendsFragment;
import com.chowchow.app.fragments.SettingsFragment;
import com.chowchow.app.utils.GcmManager;

import butterknife.Bind;
import butterknife.ButterKnife;

//test2

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    public enum MainFragment {
        SELECT_FRIENDS,
        HISTORY,
        SETTINGS
    }

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
        //GcmManager.getInstance(this).deleteGcmTokeInBackground();

        setupGCM();
    }

    private void setupGCM(){
        Intent launchIntent = getIntent();
        if ("gcm_test_app_notification_click_action".equals(launchIntent.getAction())) {
            //Log.d("123312321321312","Log.d(\"CHAT_ROOM_ID\",String.valueOf(data.getInt(UserInfoManager.CHAT_ROOM_ID)));");
            Bundle data = launchIntent.getExtras();
            data.isEmpty();
            String room_id = data.getString("test");
            Log.v("test",room_id);
            //UserInfoManager.getInstance(this).setChatRoomId(Integer.valueOf(room_id));

            /*Bundle bundle = new Bundle();
            bundle.putInt(UserInfoManager.CHAT_ROOM_ID, Integer.valueOf(room_id));

            Intent intent = new Intent(this, HomeActivity.class);
            //intent.putExtra("")
            intent.putExtras(bundle);
            startActivity(intent);*/


            //Log.d("CHAT_ROOM_ID",data.getString(UserInfoManager.CHAT_ROOM_ID));

            //data.isEmpty(); // Force the bundle to unparcel so that toString() works

            //String format = getResources().getString(R.string.notification_intent_received);

            //mLogger.log(Log.INFO, String.format(format, data));
        }
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

    public void navigateToFragment(MainFragment fragment) {
        if (fragment.equals(MainFragment.SELECT_FRIENDS)) {
            Log.d(TAG, "navigateToFragment(), navigating to fragment == SELECT_FRIENDS");
            viewPager.setCurrentItem(0);
        } else if (fragment.equals(MainFragment.HISTORY)) {
            Log.d(TAG, "navigateToFragment(), navigating to fragment == HISTORY");
            viewPager.setCurrentItem(1);
        } else if (fragment.equals(MainFragment.SETTINGS)) {
            Log.d(TAG, "navigateToFragment(), navigating to fragment == SETTINGS");
            viewPager.setCurrentItem(2);
        }
    }

    public static String getTAG() {
        return TAG;
    }
}
