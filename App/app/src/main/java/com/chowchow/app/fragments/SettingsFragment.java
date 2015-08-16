package com.chowchow.app.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowchow.app.MainActivity;
import com.chowchow.app.R;
import com.chowchow.app.utils.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {

    private static final String TAG = "SettingsFragment";

    View view;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.fragment_settings_right_nav_button)
    void onRightNavButtonPressed() {
        Log.d(TAG, "onRightNavButtonPressed()");
        ((MainActivity) getActivity()).navigateToFragment(Constants.MainFragment.HISTORY);
    }


}
