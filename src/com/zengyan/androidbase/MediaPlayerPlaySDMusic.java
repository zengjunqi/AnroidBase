package com.zengyan.androidbase;

import java.io.File;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MediaPlayerPlaySDMusic extends Activity implements OnClickListener {

	private Button btnPlay, btnPause, btnStop, btnReplay;
	private EditText etPath;
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_player_activity);

		etPath = (EditText) findViewById(R.id.etPath);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnPause = (Button) findViewById(R.id.btnPause);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnReplay = (Button) findViewById(R.id.btnRePlay);

		btnPlay.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnReplay.setOnClickListener(this);
		etPath.setText("/sdcard/1.mp3");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnPlay:// ����
			Play();
			break;
		case R.id.btnPause:
			Pause();
			break;

		case R.id.btnStop:
			Stop();
			break;
		case R.id.btnRePlay:
			RePlay();
			break;

		default:
			break;
		}

	}

	private void Play() {
		// TODO Auto-generated method stub
		String path = etPath.getText().toString();
		File file = new File(path);
		if (file.exists()) {
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(path);// ��������Դ
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// ������Ƶ��
				mediaPlayer.prepare();// ׼����ʼ���ţ���ͬ��������C�������µ��߳�ִ��,ͬ����׼������
				mediaPlayer.start();
				btnPlay.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						btnPlay.setEnabled(true);
					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Toast.makeText(this, "�ļ�������", 1000).show();
		}
	}

	private void Pause() {
		// TODO Auto-generated method stub
		if ("����".equals(btnPause.getText().toString())) {
			mediaPlayer.start();
			btnPause.setText("��ͣ");
			return;
		}
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			btnPause.setText("����");
		}
	}

	private void Stop() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			btnPlay.setEnabled(true);
			btnPause.setText("��ͣ");
		}
	}

	private void RePlay() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null ) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.seekTo(0);
				btnPause.setText("��ͣ");
			}
			else {
				mediaPlayer.seekTo(0);
				btnPause.setText("��ͣ");
				mediaPlayer.start();
			}
			
		}
		else {
			Play();
		}
	}
}
