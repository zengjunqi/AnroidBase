package com.zengyan.androidbase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallPhoneAndSendMsg extends Activity implements OnClickListener {

	Button btnCall, btnSend, btnSearch;
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
		btnSend.setOnClickListener(this);
		btnSearch = (Button) findViewById(R.id.btnSearchPhoneNo);
		btnSearch.setOnClickListener(this);

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
		case R.id.btnSearchPhoneNo:
			Intent intent=new Intent(this,ReadContact.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK)
		{
			//Log.i("ZENG", "result:"+data.getStringExtra("phoneno"));
			phoneno.setText(data.getStringExtra("phoneno"));
		}
		
	}

	private void callPhone() {
		String no = phoneno.getText().toString();
		Intent it = new Intent();
		it.setAction(Intent.ACTION_CALL);
		it.setData(Uri.parse("tel:" + no));

		startActivity(it);
	}

	private void sendMsg() {
		String no = phoneno.getText().toString();
		String msg = phonemsg.getText().toString();
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(no, null, msg, null, null);
		Toast.makeText(this, "send success", 1000).show();

	}
}
