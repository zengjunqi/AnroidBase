package com.zengyan.androidbase;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.EditText;
import android.widget.TextView;

public class GetSDInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getsdinfoactivity);
		TextView dEditText = (TextView) findViewById(R.id.txtSDInfo);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			StatFs sfs = new StatFs(Environment.getExternalStorageDirectory()
					.getPath());
		/*	long ab = sfs.getAvailableBlocksLong();
			long bc = sfs.getBlockCountLong();
			long bs = sfs.getBlockSizeLong();*/
			
			int ab = sfs.getAvailableBlocks();
			int bc = sfs.getBlockCount();
			int bs = sfs.getBlockSize();
			
			dEditText.setText("SD总大小为:"+Formatter.formatFileSize(this,bc*bs)+"\n"+"SD卡可用大小为:"+Formatter.formatFileSize(this,bc*ab));

		}
		else {
			dEditText.setText("没有SD卡!");
		}
	}

}
