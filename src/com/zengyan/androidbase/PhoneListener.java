package com.zengyan.androidbase;

import com.zengyan.androidbase.services.MyPhoneListenerService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PhoneListener extends Activity implements OnClickListener {

	Button btnStart, btnEnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.phone_listener_activity);

		btnStart = (Button) findViewById(R.id.btnStartPLisener);
		btnEnd = (Button) findViewById(R.id.btnStopPLisener);
		btnStart.setOnClickListener(this);
		btnEnd.setOnClickListener(this);
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
		default:
			break;
		}
	}
}
