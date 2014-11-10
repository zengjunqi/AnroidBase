package com.zengyan.androidbase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallPhoneAndSendMsg extends Activity implements OnClickListener {

	Button btnCall, btnSend;
	EditText phoneno, phonemsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callphoneandsendmsgactivity);

		btnCall = (Button) findViewById(R.id.btnCall);
		btnSend = (Button) findViewById(R.id.btnSend);
		phoneno = (EditText) findViewById(R.id.txtPhoneno);
		phonemsg = (EditText) findViewById(R.id.txtMsg);
		btnCall.setOnClickListener(this);
		btnSend.setOnClickListener(this);;
		


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCall:
			callPhone();

			break;
		case R.id.btnSend:
			sendMsg();
			break;
		default:
			break;
		}
	}

	private void callPhone() {
		String no = phoneno.getText().toString();
		Intent it = new Intent();
		it.setAction(Intent.ACTION_CALL);
		it.setData(Uri.parse("tel:"+no));
		
		startActivity(it);
	}
	
	private void sendMsg() {
		String no = phoneno.getText().toString();
		String msg = phonemsg.getText().toString();
		SmsManager smsManager=SmsManager.getDefault();
		smsManager.sendTextMessage(no, null,msg, null, null);
		Toast.makeText(this, "send success", 1000).show();
		
	}
}
