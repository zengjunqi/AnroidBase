package com.zengyan.androidbase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "SD±»Ð¶ÔØ»òÒÆ³ý!!!", 3000).show();
		Log.i("zengyan", "SD±»Ð¶ÔØ»òÒÆ³ý!!!");
	}

}
