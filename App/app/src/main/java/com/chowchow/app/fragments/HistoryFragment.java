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
public class HistoryFragment extends BaseFragment {

    private static final String TAG = "HistoryFragment";

    View view;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_history_left_nav_button)
    void onLeftNavButtonPressed() {
        Log.d(TAG, "onLeftNavButtonPressed()");
        ((MainActivity) getActivity()).navigateToFragment(Constants.MainFragment.SELECT_FRIENDS);
    }

    @OnClick(R.id.fragment_history_right_nav_button)
    void onRightNavButtonPressed() {
        Log.d(TAG, "onRightNavButtonPressed()");
        ((MainActivity) getActivity()).navigateToFragment(Constants.MainFragment.SETTINGS);
    }


}
