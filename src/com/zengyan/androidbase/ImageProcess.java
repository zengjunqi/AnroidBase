package com.zengyan.androidbase;

import java.io.File;
import java.util.HashMap;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class ImageProcess extends Activity {

	Button btnLoadImg,btnCSImg;
	ImageView ivImageView,iv1,iv2;
	private Bitmap alterBitmap;
	private Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.image_process_activity);

	
		
		btnLoadImg = (Button) findViewById(R.id.btnLoadImg);
		ivImageView = (ImageView) findViewById(R.id.iv);
		iv1 = (ImageView) findViewById(R.id.iv_one);
		iv2 = (ImageView) findViewById(R.id.iv_two);
		btnCSImg = (Button) findViewById(R.id.btnCSImg);
		
		 bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		iv1.setImageBitmap(bitmap);
		
		alterBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
		
		btnLoadImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Log.i("ZENG", "按垂直方向缩放,缩放比例为:"+Environment.getDataDirectory()+"/phto.bmp");
				// 1.获取屏幕的宽高信息
				WindowManager wmManager = getWindowManager();
				Point point=new Point();
				wmManager.getDefaultDisplay().getSize(point);
				int screenWidth = point.x;
				int screenHeith = point.y;
				// 2.得到图片的营宽高
				Options opts = new Options();// 解析位图的附加条件
				opts.inJustDecodeBounds = true;// 不去解析位图,只是获取这个位图的头信息
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/phto.bmp", opts);
				int bitmapWidth = opts.outWidth;
				int bitmapHeight = opts.outHeight;
				Log.i("ZENG", "屏幕宽高:" + screenWidth+","+screenHeith);
				Log.i("ZENG", "图片宽高:" + bitmapWidth+","+bitmapHeight);
				int dx = bitmapWidth / screenWidth;
				int dy = bitmapHeight / screenHeith;
				int scale=4;
				if (dx > dy && dy > 1) {
					Log.i("ZENG", "按水平方向缩放,缩放比例为:" + dx);
					scale=dx;
				}
				else if (dy > dx && dx > 1) {
					Log.i("ZENG", "按垂直方向缩放,缩放比例为:" + dy);
					scale=dy;
				}
				opts.inSampleSize=scale;
				opts.inJustDecodeBounds=false;//真正去解析位图
				 bitmap =  BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/phto.bmp", opts);
				 
				 Log.i("ZENG", "按垂直方向缩放,缩放比例为:"+Environment.getExternalStorageDirectory()+"/phto.bmp");
				ivImageView.setImageBitmap(bitmap);
			}
		});

		btnCSImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Canvas canvas=new Canvas(alterBitmap);
				Paint paint=new Paint();
				paint.setColor(Color.BLACK);
				Matrix matrix=new Matrix();
				matrix.setScale(1f, -1f);
				matrix.postTranslate(0,bitmap.getHeight());
				canvas.drawBitmap(bitmap, matrix, paint);
				iv2.setImageBitmap(alterBitmap);
			}
		});
		
	}
}
