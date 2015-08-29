package com.chowchow.app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chowchow.app.R;
import com.chowchow.app.fragments.BaseFragment;
import com.chowchow.app.fragments.SelectFriendsFragment;
import com.chowchow.app.models.Friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jackychan on 16/8/15.
 */
public class SelectFriendsListAdapter extends BaseAdapter {

    public static final String TAG = "SelectFriendsListAdapter";

    class SelectFriendsListAdapterViewHolder {

        @Bind(R.id.list_select_friends_row_name)
        TextView friendName;

        @Bind(R.id.list_select_friends_row_distance)
        TextView friendDistance;

        @Bind(R.id.list_select_friends_row_selected_tick)
        ImageView selectedTick;

        public SelectFriendsListAdapterViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private BaseFragment parentFragment;
    private List<Friend> friendsList;
    private ListView listView;
    private Map<Friend, Boolean> friendsMap; // mapping Friend object <-> boolean isSelected
    private List<Friend> selectedFriendsList;

    public SelectFriendsListAdapter(BaseFragment parentFragment, List<Friend> friends, ListView listView) {
        this.parentFragment = parentFragment;
        this.friendsList = friends;
        this.friendsMap = new HashMap<>();
        for (int i = 0; i < this.friendsList.size(); i++) {
            Friend friend = this.friendsList.get(i);
            this.friendsMap.put(friend, false); // by default, all friends unselected
        }
        this.selectedFriendsList = new ArrayList<>();

        this.listView = listView;
        this.listView.setOnItemClickListener(listener);
//        this.listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    public int getCount() {
        return friendsList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectFriendsListAdapterViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parentFragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_select_friends_row, parent, false);
            viewHolder = new SelectFriendsListAdapterViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SelectFriendsListAdapterViewHolder) convertView.getTag();
        }


        Friend friend = this.friendsList.get(position);
        viewHolder.friendName.setText(friend.getName());
        viewHolder.friendDistance.setText(friend.getDistance() + "m");

        // check if selected and highlight list item row accordingly
        boolean isSelected = this.friendsMap.get(friend);
        if (isSelected) {
            convertView.setBackgroundColor(Color.LTGRAY);
            viewHolder.friendDistance.setVisibility(View.GONE);
            viewHolder.selectedTick.setVisibility(View.VISIBLE);
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.friendDistance.setVisibility(View.VISIBLE);
            viewHolder.selectedTick.setVisibility(View.GONE);
        }

        return convertView;
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend friend = friendsList.get(position);
            boolean isSelected = friendsMap.get(friend);

            if (!isSelected) {
                Log.d(TAG, "friend " + position + " is selected");
                friendsMap.put(friend, true); // mapping selected for ltgray UI
                selectedFriendsList.add(friend); // adding to selected friends list to send
            } else {
                Log.d(TAG, "friend " + position + " is unselected");
                friendsMap.put(friend, false); // mapping unselected for transparent UI
                selectedFriendsList.remove(friend); // removing from selected friends list to send
            }

            notifyDataSetChanged();

            // check whether need to pop up action bar or not
            boolean isActionBarTriggered = ((SelectFriendsFragment) parentFragment).getActionBar().isTriggered();
            if (!selectedFriendsList.isEmpty() && !isActionBarTriggered) {
                ((SelectFriendsFragment) parentFragment).getActionBar().trigger(true);
            } else if (selectedFriendsList.isEmpty() && isActionBarTriggered) {
                ((SelectFriendsFragment) parentFragment).getActionBar().trigger(false);
            }

            // update "x friends selected" text in action bar
            if (selectedFriendsList.size() == 1) {
                String text = parentFragment.getResources().getString(R.string.action_bar_select_friends_single);
                ((SelectFriendsFragment) parentFragment).getActionBar().setItemsSelected(text);
            } else if (selectedFriendsList.size() > 1) {
                String text = String.format(parentFragment.getResources().getString(R.string.action_bar_select_friends_multi), selectedFriendsList.size());
                ((SelectFriendsFragment) parentFragment).getActionBar().setItemsSelected(text);
            }
        }
    };

    // clean up after sending request
    public void clearAllSelectedFriends() {
        for (Friend friend : selectedFriendsList) {
            friendsMap.put(friend, false);
        }

        selectedFriendsList.clear();
        Log.d(TAG, "clearAllSelectedFriends(), is selectedFriendsList empty? " + selectedFriendsList.isEmpty());

        notifyDataSetChanged();
    }

    public static String getTAG() {
        return TAG;
    }
}
