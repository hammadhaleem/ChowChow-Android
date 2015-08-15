package com.chowchow.app.fragments;

import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

/**
 * Created by jackychan on 15/8/15.
 */
public class BaseFragment extends Fragment{

    private static final String TAG = "BaseFragment";

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static String getTAG() {
        return TAG;
    }
}
