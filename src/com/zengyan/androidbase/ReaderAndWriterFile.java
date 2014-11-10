package com.zengyan.androidbase;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zengyan.androidbase.services.IOOperator;

public class ReaderAndWriterFile extends Activity implements OnClickListener {

	EditText edtext;
	Button btnRFile, btnWFile, btnRSD, btnWSD, btnRSP, btnWSP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readerandwriterfileactivity);

		edtext = (EditText) findViewById(R.id.etText);
		btnRFile = (Button) findViewById(R.id.btnReaderFile);
		btnWFile = (Button) findViewById(R.id.btnWriterFile);
		btnRSD = (Button) findViewById(R.id.btnReaderSD);
		btnWSD = (Button) findViewById(R.id.btnWriterSD);
		btnRSP = (Button) findViewById(R.id.btnReaderSP);
		btnWSP = (Button) findViewById(R.id.btnWriterSP);

		btnRFile.setOnClickListener(this);
		btnWFile.setOnClickListener(this);
		btnRSD.setOnClickListener(this);
		btnWSD.setOnClickListener(this);
		btnRSP.setOnClickListener(this);
		btnWSP.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// getFilesDir() /data/data/包名/files/文件名
		// Environment.getExternalStorageDirectory() /storage/sdcard/文件名
		switch (v.getId()) {
		case R.id.btnReaderFile:
			String textString = IOOperator.readerFile(getFilesDir(),
					"zengyan.txt");

			Toast.makeText(this, textString, 1000).show();
			break;
		case R.id.btnWriterFile:
			IOOperator.writeFile(getFilesDir(), "zengyan.txt", edtext.getText()
					.toString());

			Toast.makeText(this, "Save success", 1000).show();
			break;
		case R.id.btnReaderSD:
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				String textString1 = IOOperator.readerFile(
						Environment.getExternalStorageDirectory(),
						"zengyanSD.txt");
				Toast.makeText(this, textString1, 1000).show();
			} else {
				Toast.makeText(this, "没有SD卡!", 1000).show();
			}
			break;
		case R.id.btnWriterSD:
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				IOOperator.writeFile(Environment.getExternalStorageDirectory(),
						"zengyanSD.txt", edtext.getText().toString());

				Toast.makeText(this, "Save success", 1000).show();
			} else {
				Toast.makeText(this, "没有SD卡!", 1000).show();
			}

			break;
		case R.id.btnReaderSP:
			readerSP();

			break;
		case R.id.btnWriterSP:
			saveSP();

			break;

		default:
			break;
		}

	}

	private void saveSP() {

		SharedPreferences sPreferences = getSharedPreferences("zyj",
				MODE_PRIVATE);
		Editor editor = sPreferences.edit();
		editor.putString("text", edtext.getText().toString());
		editor.commit();
		Toast.makeText(this, "Save share preferences success", 1000).show();
	}

	private void readerSP() {

		SharedPreferences sPreferences = getSharedPreferences("zyj",
				MODE_PRIVATE);
		String text = sPreferences.getString("text", null);
		Toast.makeText(this, text, 5000).show();
	}
}
