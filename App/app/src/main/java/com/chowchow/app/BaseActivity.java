package com.chowchow.app;

import android.support.v4.app.FragmentActivity;

/**
 * Created by jackychan on 15/8/15.
 */
public class BaseActivity extends FragmentActivity{

    private static final String TAG = "BaseActivity";

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseApplication.onActivityPause();
    }

    public static String getTAG() {
        return TAG;
    }
}
