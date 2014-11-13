package com.zengyan.androidbase;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MyAnimation extends Activity {

	private ImageView iv,iv1;
	private AnimationDrawable mAnimationDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.myanim_activity);
		iv = (ImageView) findViewById(R.id.iv_lineanim);
		iv1 = (ImageView) findViewById(R.id.iv_draanim);
		// 把xml文件的动画资源设置为iv背景
		iv1.setBackgroundResource(R.drawable.girl);
		// 获取设置的动画资源。 执行可能需要花费一定的时间
		mAnimationDrawable = (AnimationDrawable) iv1.getBackground();
		mAnimationDrawable.start();
	}

	// 透明度动画
	public void alpha(View view) {
		Animation aa = AnimationUtils.loadAnimation(this, R.anim.alpha);
		iv.startAnimation(aa);
	}

	// 位移动画
	public void trans(View view) {
		Animation ta = AnimationUtils.loadAnimation(this, R.anim.trans);
		iv.startAnimation(ta);
	}

	// 缩放动画
	public void scale(View view) {
		Animation sa = AnimationUtils.loadAnimation(this, R.anim.scale);
		iv.startAnimation(sa);
	}

	// 旋转动画
	public void rotate(View view) {
		Animation ra = AnimationUtils.loadAnimation(this, R.anim.rotate);
		iv.startAnimation(ra);
	}

	// 动画组合
	public void set(View view) {
		Animation set = AnimationUtils.loadAnimation(this, R.anim.set);
		iv.startAnimation(set);
	}
	
	/*动画的两种写法,一种写XML,一种写代码
	//透明度动画
	public void alpha(View view){
		AlphaAnimation  aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(2000);
		aa.setRepeatCount(1);
		aa.setRepeatMode(Animation.REVERSE);
		aa.setFillAfter(true);
		iv.startAnimation(aa);
	}
	//位移动画
	public void trans(View view){
		TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f);
		ta.setDuration(2000);
		ta.setRepeatCount(1);
		ta.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ta);
	}
	//缩放动画
	public void scale(View view){
		ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(sa);
	}
	
	//旋转动画
	public void rotate(View view){
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ra);
	}
	//动画组合
	public void set(View view){
		AnimationSet set = new AnimationSet(false);
		TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f, 
				Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f);
		ta.setDuration(2000);
		ta.setRepeatCount(1);
		ta.setRepeatMode(Animation.REVERSE);
		ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		set.addAnimation(ra);
		//set.addAnimation(ta);
		set.addAnimation(sa);
		iv.startAnimation(set);
	}
	 */
}
