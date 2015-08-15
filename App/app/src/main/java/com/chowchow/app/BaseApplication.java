package com.chowchow.app;

import android.app.Application;

/**
 * Created by jackychan on 15/8/15.
 */
public class BaseApplication extends Application {

    private final static String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        // App configs belong here, e.g. typeface config/init
    }

    /**********************************************************
    *  App running state (foreground or background) related   *
    * ******************************************************* */

    private static boolean isInForeground;

    public static void onActivityResume() {
        isInForeground = true;
    }

    public static void onActivityPause() {
        isInForeground = false;
    }

    public static boolean isInForeground() {
        return isInForeground;
    }

    public static String getTAG() {
        return TAG;
    }
}
