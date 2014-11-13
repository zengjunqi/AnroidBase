package com.zengyan.androidbase;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MediaPlayerPlayVideo extends Activity implements OnClickListener {

	private Button btnPlay, btnPause, btnStop, btnReplay;
	private EditText etPath;
	private MediaPlayer mediaPlayer;
	private SurfaceView surfaceView;
	private SurfaceHolder hoder;
	private int position;
	private String path;
	private SeekBar sBar;
	private Timer timer;
	private TimerTask task;

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
		surfaceView = (SurfaceView) findViewById(R.id.sv);
		hoder = surfaceView.getHolder();
		hoder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sBar = (SeekBar) findViewById(R.id.sb1);
		btnPlay.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnReplay.setOnClickListener(this);
		etPath.setText("/sdcard/3.3gp");

		hoder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				Log.i("ZENG", "surfaceDestoryed");
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					position = mediaPlayer.getCurrentPosition();
					timer.cancel();
					task.cancel();
					timer = null;
					task = null;
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = null;
					
				}

			}

			@Override
			public void surfaceCreated(SurfaceHolder holders) {
				// TODO Auto-generated method stub
				if (position > 0) {
					try {
						mediaPlayer = new MediaPlayer();
						mediaPlayer.setDataSource(path);// 设置数据源
						mediaPlayer
								.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置音频流
						// mediaPlayer.prepare();//
						// 准备开始播放，如同播放是由C代码在新的线程执行,同步的准备方法
						mediaPlayer.setDisplay(holders);
						mediaPlayer.prepare();
						mediaPlayer.start();
						mediaPlayer.seekTo(position);
						btnPlay.setEnabled(false);

						mediaPlayer
								.setOnCompletionListener(new OnCompletionListener() {

									@Override
									public void onCompletion(MediaPlayer mp) {
										// TODO Auto-generated method stub
										btnPlay.setEnabled(true);
									}
								});
						setTimer();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});

		sBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int p = seekBar.getProgress();
				mediaPlayer.seekTo(p);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

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
		path = etPath.getText().toString();
		File file = new File(path);
		if (file.exists()) {
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(path);// 设置数据源
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置音频流
				// mediaPlayer.prepare();// 准备开始播放，如同播放是由C代码在新的线程执行,同步的准备方法

				mediaPlayer.setDisplay(hoder);
				mediaPlayer.prepare();
				mediaPlayer.start();
				btnPlay.setEnabled(false);
				setTimer();
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
			Toast.makeText(this, "文件找不到", 1000).show();
		}
	}

	private void setTimer() {
		int max = mediaPlayer.getDuration();
		sBar.setMax(max);
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				sBar.setProgress(mediaPlayer.getCurrentPosition());
			}
		};
		timer.schedule(task, 0, 500);
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
			timer.cancel();
			task.cancel();
			timer = null;
			task = null;
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			btnPlay.setEnabled(true);
			btnPause.setText("暂停");
		}
	}

	private void RePlay() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.seekTo(0);
				btnPause.setText("暂停");
			} else {
				mediaPlayer.seekTo(0);
				btnPause.setText("暂停");
				mediaPlayer.start();
			}

		} else {
			Play();
		}
	}
}
