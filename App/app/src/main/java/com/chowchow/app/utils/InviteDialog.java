package com.chowchow.app.utils;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chowchow.app.R;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lk on 23/8/15.
 */
public class InviteDialog extends Dialog implements View.OnClickListener{

	//TextView name;
	@Bind(R.id.tv_name) TextView name;
	@Bind(R.id.tv_invite) TextView invite;
	@Bind(R.id.iv_u_icon) ImageView u_icon;
	@Bind(R.id.iv_ok) ImageView tick;
	@Bind(R.id.iv_no) ImageView cross;
	@Bind(R.id.iv_eaten) ImageView eaten;
	String room_id;
	public InviteDialog(Context context, String room_id, String u_name){
		super(context);
		ButterKnife.bind(context, this);
		setContentView(R.layout.dialog_invite);
		setName(u_name);
	}

	public void setName(String inviter){
		name.setText(inviter);
		setInviteText(inviter);
	}

	public void setInviteText(String inviter){
		invite.setText(" Wanna eat with " + inviter + " ?");
	}

	@Override
	public void onClick(View v) {
		if(v == tick){
			Log.v("join", room_id);
		}else if(v == cross){
			Log.v("cross", room_id);
		}else if(v == eaten){
			Log.v("eaten", room_id);
		}
		this.dismiss();
	}
}