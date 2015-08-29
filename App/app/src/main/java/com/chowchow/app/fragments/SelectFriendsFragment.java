package com.chowchow.app.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chowchow.app.GCM_.logic.InstanceIdHelper;
import com.chowchow.app.MainActivity;
import com.chowchow.app.R;
import com.chowchow.app.utils.Constants;
import com.chowchow.app.utils.GcmManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFriendsFragment extends BaseFragment {

    private static final String TAG = "SelectFriendsFragment";

    View view;
    private InstanceIdHelper mInstanceIdHelper;

    public SelectFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select_friends, container, false);
        ButterKnife.bind(this, view);
        /*mInstanceIdHelper = new InstanceIdHelper(this.getActivity().getApplicationContext());
        mInstanceIdHelper.getGcmTokenInBackground(Constants.GCM_SENDER_ID);*/
        //GcmManager.getInstance(this.getActivity().getApplicationContext()).deleteGcmTokeInBackground();
        GcmManager.getInstance(this.getActivity().getApplicationContext()).getGcmTokenInBackground();


        return view;
    }

    @OnClick(R.id.fragment_select_friends_right_nav_button)
    void onRightNavButtonPressed() {
        Log.d(TAG, "onRightNavButtonPressed()");
        ((MainActivity) getActivity()).navigateToFragment(MainActivity.MainFragment.HISTORY);
    }

    @OnClick(R.id.btn_send_gcm_test)
    void OnTest(){
        GcmManager.getInstance(this.getActivity().getApplicationContext()).doGcmSend(
            GcmManager.getInstance(this.getActivity().getApplicationContext()).getToken()
        );
    }





}
