package com.zengyan.androidbase;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zengyan.androidbase.interfaces.IMiddlePerson;
import com.zengyan.androidbase.services.BindService;
import com.zengyan.androidbase.services.MyPhoneListenerService;

public class PhoneListener extends Activity implements OnClickListener {

	Button btnStart, btnEnd, btnSBS, btnEBS, btnCBS;
	private MyConn conn;
	private IMiddlePerson mpMiddlePerson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_listener_activity);

		btnStart = (Button) findViewById(R.id.btnStartPLisener);
		btnEnd = (Button) findViewById(R.id.btnStopPLisener);
		btnSBS = (Button) findViewById(R.id.btnStartBS);
		btnEBS = (Button) findViewById(R.id.btnStopBS);
		btnCBS = (Button) findViewById(R.id.btnCallBSMethod);

		btnStart.setOnClickListener(this);
		btnEnd.setOnClickListener(this);
		btnSBS.setOnClickListener(this);
		btnEBS.setOnClickListener(this);
		btnCBS.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnStartPLisener:

			Intent intent = new Intent(this, MyPhoneListenerService.class);
			startService(intent);

			break;
		case R.id.btnStopPLisener:

			Intent it = new Intent(this, MyPhoneListenerService.class);
			stopService(it);
			break;
		case R.id.btnStartBS:

			Intent bsinteIntent = new Intent(this, BindService.class);
			conn=new MyConn();
			bindService(bsinteIntent, conn, BIND_AUTO_CREATE);
			break;

		case R.id.btnStopBS:
			unbindService(conn);
			mpMiddlePerson = null;
			break;

		case R.id.btnCallBSMethod:

			mpMiddlePerson.callMethodInService();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}

	private class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("ZENG", "Bind Service Connect");
			mpMiddlePerson = (IMiddlePerson) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	}

}
