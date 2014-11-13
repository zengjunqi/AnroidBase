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
		case R.id.btnPlay:// 播放
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
				mediaPlayer.setDataSource(path);// 设置数据源
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置音频流
				mediaPlayer.prepare();// 准备开始播放，如同播放是由C代码在新的线程执行,同步的准备方法
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
			Toast.makeText(this, "文件不存在", 1000).show();
		}
	}

	private void Pause() {
		// TODO Auto-generated method stub
		if ("继续".equals(btnPause.getText().toString())) {
			mediaPlayer.start();
			btnPause.setText("暂停");
			return;
		}
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			btnPause.setText("继续");
		}
	}

	private void Stop() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			btnPlay.setEnabled(true);
			btnPause.setText("暂停");
		}
	}

	private void RePlay() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null ) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.seekTo(0);
				btnPause.setText("暂停");
			}
			else {
				mediaPlayer.seekTo(0);
				btnPause.setText("暂停");
				mediaPlayer.start();
			}
			
		}
		else {
			Play();
		}
	}
}
