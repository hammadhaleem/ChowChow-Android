package com.chowchow.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.chowchow.app.GCM_.logic.GcmServerSideSender;
import com.chowchow.app.GCM_.logic.Message;
import com.chowchow.app.GCM_.model.Sender;
import com.chowchow.app.GCM_.model.SenderCollection;
import com.chowchow.app.GCM_.service.LoggingService;
import com.chowchow.app.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by lk on 23/8/15.
 */


public class GcmManager extends ManagerBase{
	//public LoggingService.Logger mLogger;
	//public Activity activity;
	protected static GcmManager instance = null;
	private SenderCollection mSenders;
	private String mToken;


	public GcmManager(Context context){
		super(context);
		mToken = null;
	}

	public static GcmManager getInstance(Context context) {
		if(instance == null) {
			instance = new GcmManager(context);
		}
		return instance;
	}

	public void doGcmSend(final String registrationId) {
		final Message.Builder messageBuilder = new Message.Builder();

		Log.d("start","doGCMSend");
		Log.v("registrationId",registrationId);
		String icon = "logo";

		if (!TextUtils.isEmpty(icon)) {
			messageBuilder.notificationIcon(icon);
		} else {
        	Log.d("Error", context.getResources().getString(R.string.notification_send_fail_icon_required));
			return;
		}

		String title = "test_title";
		if (!TextUtils.isEmpty(title)) {
			messageBuilder.notificationTitle(title);
		} else {
			Log.d("Error", context.getResources().getString(R.string.notification_send_fail_title_required));
			return;
		}

		String body = "body";
		if (!TextUtils.isEmpty(body)) {
			messageBuilder.notificationBody(body);
		}

		String clickAction = "gcm_test_app_notification_click_action";
		if (!TextUtils.isEmpty(clickAction)) {
			messageBuilder.notificationClickAction(clickAction);
		}

		String sound = "sound";
		if (!TextUtils.isEmpty(sound)) {
			messageBuilder.notificationSound(sound);
		}

		String tag = "tag";
		if (!TextUtils.isEmpty(tag)) {
			messageBuilder.notificationTag(tag);
		}

		final String apiKey = Constants.API_KEY;

		messageBuilder.addData("test", "1"); //TODO: need to be changed

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				GcmServerSideSender sender = new GcmServerSideSender(apiKey);
				try {
					sender.sendHttpJsonDownstreamMessage(registrationId, messageBuilder.build());
				} catch (final IOException e) {
					return e.getMessage();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				Log.d("sent", "sent");
			}
		}.execute();
	}

	public void getGcmTokenInBackground() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					mToken = InstanceID.getInstance(context).getToken(Constants.GCM_SENDER_ID,
									GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
					Log.d("okok", "registration succeeded." + "\nConstants.GCM_SENDER_ID: " + Constants.GCM_SENDER_ID + "\ntoken: " + mToken);

				} catch (final IOException e) {
					Log.d("INFO","error");
				}
				return null;
			}
		}.execute();
	}

	public void deleteGcmTokeInBackground() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					InstanceID.getInstance(context).deleteToken(Constants.GCM_SENDER_ID,
							GoogleCloudMessaging.INSTANCE_ID_SCOPE);
					Log.v("gcm_info", "delete token succeeded." +
							"\nConstants.GCM_SENDER_ID: " + Constants.GCM_SENDER_ID);
				} catch (final IOException e) {
					Log.v("gcm_info", "remove token failed." +
							"\nConstants.GCM_SENDER_ID: " + Constants.GCM_SENDER_ID + "\nerror: " + e.getMessage());
				}
				return null;
			}
		}.execute();
	}

	public String getToken(){
		return mToken;
	}

}
