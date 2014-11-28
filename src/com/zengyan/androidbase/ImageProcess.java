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
				 Log.i("ZENG", "����ֱ��������,���ű���Ϊ:"+Environment.getDataDirectory()+"/phto.bmp");
				// 1.��ȡ��Ļ�Ŀ����Ϣ
				WindowManager wmManager = getWindowManager();
				Point point=new Point();
				wmManager.getDefaultDisplay().getSize(point);
				int screenWidth = point.x;
				int screenHeith = point.y;
				// 2.�õ�ͼƬ��Ӫ���
				Options opts = new Options();// ����λͼ�ĸ�������
				opts.inJustDecodeBounds = true;// ��ȥ����λͼ,ֻ�ǻ�ȡ���λͼ��ͷ��Ϣ
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/phto.bmp", opts);
				int bitmapWidth = opts.outWidth;
				int bitmapHeight = opts.outHeight;
				Log.i("ZENG", "��Ļ���:" + screenWidth+","+screenHeith);
				Log.i("ZENG", "ͼƬ���:" + bitmapWidth+","+bitmapHeight);
				int dx = bitmapWidth / screenWidth;
				int dy = bitmapHeight / screenHeith;
				int scale=4;
				if (dx > dy && dy > 1) {
					Log.i("ZENG", "��ˮƽ��������,���ű���Ϊ:" + dx);
					scale=dx;
				}
				else if (dy > dx && dx > 1) {
					Log.i("ZENG", "����ֱ��������,���ű���Ϊ:" + dy);
					scale=dy;
				}
				opts.inSampleSize=scale;
				opts.inJustDecodeBounds=false;//����ȥ����λͼ
				 bitmap =  BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/phto.bmp", opts);
				 
				 Log.i("ZENG", "����ֱ��������,���ű���Ϊ:"+Environment.getExternalStorageDirectory()+"/phto.bmp");
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
