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
				case TelephonyManager.CALL_STATE_IDLE:// ����״̬

					if (mediaRecorder!=null) {
						mediaRecorder.stop();
						mediaRecorder.release();
						mediaRecorder=null;
						Log.i("ZENG", "�Ѿ�¼�����,׼���ϴ���������");
						Toast.makeText(getApplicationContext(), "�Ѿ�¼�����,׼���ϴ���������", 1000).show();
					}
					break;
				case TelephonyManager.CALL_STATE_RINGING:// ����״̬

					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:// ͨ��״̬
					// ��ʼ¼��
					// 1.ʵ����¼��������
					mediaRecorder = new MediaRecorder();

					// 2.ָ��¼��������Դ
					mediaRecorder
							.setAudioSource(MediaRecorder.AudioSource.MIC);
					// 3.����¼�Ƶ��ļ�����ĸ�ʽ
					mediaRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					// 4.ָ��¼���ļ�������
					File file = new File(
							Environment.getExternalStorageDirectory(),
							System.currentTimeMillis() + ".3gp");
					mediaRecorder.setOutputFile(file.getAbsolutePath());

					Log.i("ZENG", file.getAbsolutePath());
					// 5.������Ƶ�ı���
					mediaRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					// 6.׼����ʼ¼��
					mediaRecorder.prepare();
					// 7.��ʼ¼��
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
