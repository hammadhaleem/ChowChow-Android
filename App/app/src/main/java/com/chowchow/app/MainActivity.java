package com.chowchow.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
            final String room_id = data.getString("test");
            Log.v("test",room_id);

            if (room_id.compareTo("0")!=0) {
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_invite);
                dialog.show();


                TextView name = (TextView) dialog.findViewById(R.id.tv_name);
                TextView invite = (TextView) dialog.findViewById(R.id.tv_invite);
                ImageView u_icon = (ImageView) dialog.findViewById(R.id.iv_u_icon);
                ImageView tick = (ImageView) dialog.findViewById(R.id.iv_ok);
                ImageView cross = (ImageView) dialog.findViewById(R.id.iv_no);
                ImageView eaten = (ImageView) dialog.findViewById(R.id.iv_eaten);

                //String uid = "111198045882720";
                String uname ="Harry";
                name.setText(uname);
                invite.setText(" Wanna eat with " + uname + " ?");
                u_icon.setImageResource(R.drawable.icon_harry);

                View.OnClickListener onclick =new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickDialog(v, dialog, room_id);
                    }
                };

                tick.setOnClickListener(onclick);
                cross.setOnClickListener(onclick);
                eaten.setOnClickListener(onclick);
            }
        }
    }



    private void clickDialog(View v, Dialog dialog, String room_id){
        int itemId = v.getId();
        if(itemId == R.id.iv_ok){
            Log.v("join",room_id);
        }else if(itemId == R.id.iv_no){
            Log.v("cross",room_id);
        }else if(itemId == R.id.iv_eaten){
            Log.v("eaten",room_id);
        }
        dialog.dismiss();
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
