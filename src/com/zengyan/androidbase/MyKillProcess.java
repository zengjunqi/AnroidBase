package com.zengyan.androidbase;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyKillProcess extends Activity {
	private Button btnKill;
	private EditText etpackage;
	private ActivityManager am;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.killprocess_activity);
		btnKill=(Button) findViewById(R.id.btnKill);
		etpackage=(EditText) findViewById(R.id.txtPackage);
		am=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
		btnKill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				am.killBackgroundProcesses(etpackage.getText().toString());//能杀死空进程和后台进程
				Toast.makeText(MyKillProcess.this, "杀死成功", 1000).show();
				
				
				//开启手机的安装程序Activity
				/*Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
				startActivity(intent);*/
			}
		});

	}
}
