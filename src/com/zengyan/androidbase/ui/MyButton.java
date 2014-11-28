package com.zengyan.androidbase.ui;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.zengyan.androidbase.R;

public class MyButton extends View implements OnClickListener {

	private Bitmap backgrouBitmap;
	private Bitmap beforebBitmap;
	private Paint paint;
	boolean turnon;
	private float slideBtn_left = 0;
	private boolean curStatue = false;
	private int backgrouBitmapID;
	private int beforebBitmapID;

	// 代码New时创建的方法
	public MyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	/*	int count = attrs.getAttributeCount();
		for (int i = 0; i < count; i++) {
			System.out.println("name:" + attrs.getAttributeName(i));
			System.out.println("value:" + attrs.getAttributeValue(i));
		}*/

		TypedArray taArray = context.obtainStyledAttributes(attrs,
				R.styleable.MyBtn);
		int tacount = taArray.getIndexCount();
		for (int i = 0; i < tacount; i++) {
			int index = taArray.getIndex(i);// 获取到定义属性的ID
			switch (index) {
			case R.styleable.MyBtn_backDrable:
				// taArray.getDrawable(index);
				backgrouBitmapID = taArray.getResourceId(index, -1);
				if (backgrouBitmapID == -1) {

					throw new RuntimeException("请设置背景图片");
				}
				backgrouBitmap = BitmapFactory.decodeResource(getResources(),
						backgrouBitmapID);

				break;
			case R.styleable.MyBtn_foreDrable:
				// taArray.getDrawable(index);
				beforebBitmapID = taArray.getResourceId(index, -1);
				if (beforebBitmapID == -1) {

					throw new RuntimeException("请设置背景图片");
				}
				beforebBitmap = BitmapFactory.decodeResource(getResources(),
						beforebBitmapID);
				break;

			case R.styleable.MyBtn_turnon:
				curStatue = taArray.getBoolean(index, false);

				break;

			}
		}

		initView(context);

	}

	private void initView(Context context) {
		// TODO Auto-generated method stub
		paint = new Paint();
		paint.setAntiAlias(true);// 抗矩齿

		// backgrouBitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.switch_background);
		// beforebBitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.slide_button);

		setOnClickListener(this);
		flushState();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// 设置当前VIEW的大小, 单位是像素值
		setMeasuredDimension(backgrouBitmap.getWidth(),
				backgrouBitmap.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {

		paint.setColor(Color.BLACK);
		canvas.drawBitmap(backgrouBitmap, 0, 0, paint);
		canvas.drawBitmap(beforebBitmap, slideBtn_left, 0, paint);
	}

	private boolean isDrag = false;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (!isDrag) {
			curStatue = !curStatue;
			flushState();
		}

	}

	private void flushState() {
		// TODO Auto-generated method stub
		if (curStatue) {
			slideBtn_left = backgrouBitmap.getWidth()
					- beforebBitmap.getWidth();
		} else {
			slideBtn_left = 0;
		}
		invalidate();// 重新执行onDraw方法
	}

	private int firstx;
	private int lastx;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstx = lastx = (int) event.getX();
			isDrag = false;
			break;

		case MotionEvent.ACTION_MOVE:
			// 判断是否发生拖动
			if (Math.abs(event.getX() - firstx) > 5) {
				isDrag = true;
			}

			int dis = (int) (event.getX() - lastx);
			lastx = (int) event.getX();
			slideBtn_left = slideBtn_left + dis;

			break;

		case MotionEvent.ACTION_UP:
			if (isDrag) {

				int maxleft = backgrouBitmap.getWidth()
						- beforebBitmap.getWidth();
				if (slideBtn_left > maxleft / 2) {
					curStatue = true;
				} else {
					curStatue = false;
				}
				flushState();
			}
			break;
		}

		flushView();

		return true;

	}

	private void flushView() {
		int maxleft = backgrouBitmap.getWidth() - beforebBitmap.getWidth();
		slideBtn_left = slideBtn_left > 0 ? slideBtn_left : 0;
		slideBtn_left = slideBtn_left < maxleft ? slideBtn_left : maxleft;
		invalidate();
	}
}
