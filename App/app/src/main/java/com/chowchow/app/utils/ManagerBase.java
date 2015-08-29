package com.chowchow.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.chowchow.app.GCM_.logic.GcmServerSideSender;
import com.chowchow.app.GCM_.logic.Message;
import com.chowchow.app.GCM_.service.LoggingService;
import com.chowchow.app.R;

import java.io.IOException;

/**
 * Created by lk on 23/8/15.
 */


public class ManagerBase {
	//protected static ManagerBase instance = null;
	protected static Context context;


	public ManagerBase(Context context) {
		this.context = context;
	}

	/*public static ManagerBase getInstance(Context context) {
		if(instance == null) {
			instance = new ManagerBase(context);
		}
		return instance;
	}*/
}
