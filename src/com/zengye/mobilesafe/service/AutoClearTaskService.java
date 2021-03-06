package com.zengye.mobilesafe.service;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class AutoClearTaskService extends Service {

	public static final String TAG = "AutoClearTaskService";
	private ScreenOffReceiver receiver;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	    receiver = new ScreenOffReceiver();
		registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		unregisterReceiver(receiver);
		receiver = null;
		super.onDestroy();
		
	}
	class ScreenOffReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.i(TAG, "锁屏清理");
			ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
		    List<RunningAppProcessInfo>	infos =am.getRunningAppProcesses();
		    for (RunningAppProcessInfo info : infos) {
		    	am.killBackgroundProcesses(info.processName);
			}
			
		}
		
	}
}
