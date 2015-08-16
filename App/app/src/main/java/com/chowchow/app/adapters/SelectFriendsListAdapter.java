package com.chowchow.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chowchow.app.R;
import com.chowchow.app.models.Friend;

import java.util.List;

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

        public SelectFriendsListAdapterViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private Activity parentActivity;
    private List<Friend> friends;
    private ListView listView;

    public SelectFriendsListAdapter(Activity parentActivity, List<Friend> friends, ListView listView) {
        this.parentActivity = parentActivity;
        this.friends = friends;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SelectFriendsListAdapterViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_select_friends_row, parent, false);
            viewHolder = new SelectFriendsListAdapterViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SelectFriendsListAdapterViewHolder) convertView.getTag();
        }

        Friend friend = friends.get(position);
        viewHolder.friendName.setText(friend.getName());
        viewHolder.friendDistance.setText(friend.getDistance() + "m");

        return convertView;
    }

    public static String getTAG() {
        return TAG;
    }
}
