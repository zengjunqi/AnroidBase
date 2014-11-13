package com.zengyan.androidbase;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class SiYiFu extends Activity {

	private ImageView ivPre, ivAfter;
	private Bitmap alterBitmap;
	private Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiyifu_activity);
		ivPre = (ImageView) findViewById(R.id.iv_pre);
		// ivAfter = (ImageView) findViewById(R.id.iv_after);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pre15);
		alterBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		canvas = new Canvas(alterBitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		ivPre.setImageBitmap(alterBitmap);

		ivPre.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX();
					int y = (int) event.getY();

					for (int i = -20; i < 20; i++) {
						for (int j = -20; j < 20; j++) {

							try {
								alterBitmap.setPixel(x + i, y + j,
										Color.TRANSPARENT);
								// Log.i("ZENG", x + i+"=="+ y + j);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					ivPre.setImageBitmap(alterBitmap);

					break;
				case MotionEvent.ACTION_UP:
					MediaPlayer.create(getApplicationContext(), R.raw.higirl)
							.start();
					break;
				default:
					break;
				}

				return true;
			}
		});

	}
}
