package com.zengyan.androidbase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private String[] items = { "CallPhoneAndSendMsg", "ReaderAndWriterFile",
			"XmlParserAct", "GetSDInfo", "Sqlite", "PhoneListener",
			"ImageProcess", "SiYiFu" ,"MediaPlayer Play Muisc","MediaPlayer Play Network Music","MediaPlayer Play Video"};
	private Class[] cc = { CallPhoneAndSendMsg.class,
			ReaderAndWriterFile.class, XmlParserAct.class, GetSDInfo.class,
			Sqlite.class, PhoneListener.class, ImageProcess.class, SiYiFu.class,
			MediaPlayerPlaySDMusic.class,MediaPlayerPlayNetworkMusic.class,MediaPlayerPlayVideo.class};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) this.findViewById(R.id.lv);
		setLVAdapter();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Intent it = new Intent(MainActivity.this, cc[arg2]);
				it.putExtra("title", items[arg2]);
				startActivity(it);

			}

		});

	}

	private void setLVAdapter() {
		/*
		 * List<String> list=new ArrayList<String>(); for(int
		 * i=0;i<items.length;i++){ list.add(items[i]); }
		 */
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, items);
		lv.setAdapter(adapter);
	}

}
