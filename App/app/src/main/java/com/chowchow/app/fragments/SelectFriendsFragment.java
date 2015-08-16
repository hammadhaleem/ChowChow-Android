package com.chowchow.app.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chowchow.app.MainActivity;
import com.chowchow.app.R;
import com.chowchow.app.adapters.SelectFriendsListAdapter;
import com.chowchow.app.models.Friend;
import com.chowchow.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFriendsFragment extends BaseFragment {

    private static final String TAG = "SelectFriendsFragment";

    View view;
    private List<Friend> friends;
    private SelectFriendsListAdapter adapter;

    @Bind(R.id.fragment_select_friends_list_view)
    ListView listView;

    public SelectFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select_friends, container, false);
        ButterKnife.bind(this, view);

        initListView();

        return view;
    }

    private void initListView() {
        initMockFriends();
        adapter = new SelectFriendsListAdapter(getActivity(), friends, listView);
        listView.setAdapter(adapter);
    }

    // setup hardcoded data
    private void initMockFriends() {
        friends = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Friend.Builder builder = new Friend.Builder(String.valueOf(i));
            builder.setName("Friend " + i);
            builder.setDistance(i * 100);
            Friend friend = new Friend(builder);
            friends.add(friend);
        }
    }


    @OnClick(R.id.fragment_select_friends_right_nav_button)
    void onRightNavButtonPressed() {
        Log.d(TAG, "onRightNavButtonPressed()");
        ((MainActivity) getActivity()).navigateToFragment(Constants.MainFragment.HISTORY);
    }


}
