package com.zengyan.androidbase.services;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneListenerService extends Service {

	private TelephonyManager tm;
	private Mylistener listener;
	private MediaRecorder mediaRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		listener = new Mylistener();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
	}

	private class Mylistener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);

			try {
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:// 空闲状态

					if (mediaRecorder!=null) {
						mediaRecorder.stop();
						mediaRecorder.release();
						mediaRecorder=null;
						Log.i("ZENG", "已经录制完毕,准备上传到服务器");
						Toast.makeText(getApplicationContext(), "已经录制完毕,准备上传到服务器", 1000).show();
					}
					break;
				case TelephonyManager.CALL_STATE_RINGING:// 响铃状态

					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:// 通话状态
					// 开始录音
					// 1.实例化录音机对象
					mediaRecorder = new MediaRecorder();

					// 2.指定录音机声音源
					mediaRecorder
							.setAudioSource(MediaRecorder.AudioSource.MIC);
					// 3.设置录制的文件输出的格式
					mediaRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					// 4.指定录音文件的名称
					File file = new File(
							Environment.getExternalStorageDirectory(),
							System.currentTimeMillis() + ".3gp");
					mediaRecorder.setOutputFile(file.getAbsolutePath());

					Log.i("ZENG", file.getAbsolutePath());
					// 5.设置音频的编码
					mediaRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					// 6.准备开始录音
					mediaRecorder.prepare();
					// 7.开始录音
					mediaRecorder.start();
					break;

				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
