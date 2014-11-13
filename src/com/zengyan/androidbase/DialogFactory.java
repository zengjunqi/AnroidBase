package com.zengyan.androidbase;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class DialogFactory extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_factory_activity);
	}

	public void click1(View view){
		//对话框的创建器
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("我是对话框");
		builder.setMessage("对话框显示的内容");
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "确定被点击了", 0).show();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//什么都不写默认实现就是关闭掉对话框
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	/**
	 * 单选对话框
	 * @param view
	 */
	public void click2(View view){
		//对话框的创建器
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择您的性别");
		final String[] items = {"男","女","未知"};
		builder.setSingleChoiceItems(items, 2, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "您的性别："+items[which], 0).show();
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	/**
	 * 多选对话框
	 * @param view
	 */
	public void click3(View view){
		//对话框的创建器
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("请选择你最爱吃的水果");
		final String[] items={"苹果","梨","菠萝","香蕉","黄瓜"};
		final boolean[] result =new boolean[]{true,false,true,false,false};
		builder.setMultiChoiceItems(items, result, new OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(getApplicationContext(), items[which]+isChecked, 0).show();
				result[which] = isChecked;
			}
		});
		builder.setPositiveButton("提交", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<result.length;i++){
					if(result[i]){
						sb.append(items[i]+",");
					}
				}
				Toast.makeText(getApplicationContext(), "您选中了，"+sb.toString(), 0).show();
			}
		});
		//builder.create().show();
		
		builder.show();
	}
	
	//进度条对话框
	public void click4(View view){
		ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("提醒");
		pd.setMessage("正在加载数据...请稍等。");
		pd.show();
	}
	//带进度的进度条对话框
	public void click5(View view){
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("提醒");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		pd.setMessage("正在加载数据...请稍等。");
		pd.show();
		new Thread(){
			public void run() {
				for(int i = 0;i<100;i++){
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pd.setProgress(i);
				}
				pd.dismiss();
			};
		}.start();
	}
} 
