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
		//�Ի���Ĵ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("���ǶԻ���");
		builder.setMessage("�Ի�����ʾ������");
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "ȷ���������", 0).show();
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//ʲô����дĬ��ʵ�־��ǹرյ��Ի���
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	/**
	 * ��ѡ�Ի���
	 * @param view
	 */
	public void click2(View view){
		//�Ի���Ĵ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ�������Ա�");
		final String[] items = {"��","Ů","δ֪"};
		builder.setSingleChoiceItems(items, 2, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "�����Ա�"+items[which], 0).show();
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	/**
	 * ��ѡ�Ի���
	 * @param view
	 */
	public void click3(View view){
		//�Ի���Ĵ�����
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ������Ե�ˮ��");
		final String[] items={"ƻ��","��","����","�㽶","�ƹ�"};
		final boolean[] result =new boolean[]{true,false,true,false,false};
		builder.setMultiChoiceItems(items, result, new OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(getApplicationContext(), items[which]+isChecked, 0).show();
				result[which] = isChecked;
			}
		});
		builder.setPositiveButton("�ύ", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<result.length;i++){
					if(result[i]){
						sb.append(items[i]+",");
					}
				}
				Toast.makeText(getApplicationContext(), "��ѡ���ˣ�"+sb.toString(), 0).show();
			}
		});
		//builder.create().show();
		
		builder.show();
	}
	
	//�������Ի���
	public void click4(View view){
		ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("����");
		pd.setMessage("���ڼ�������...���Եȡ�");
		pd.show();
	}
	//�����ȵĽ������Ի���
	public void click5(View view){
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("����");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		pd.setMessage("���ڼ�������...���Եȡ�");
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
