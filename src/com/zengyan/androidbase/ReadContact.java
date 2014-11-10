package com.zengyan.androidbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zengyan.androidbase.model.Person;

public class ReadContact extends Activity {

	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sqliteactivity);
		lv = (ListView) findViewById(R.id.lvsqlite);

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "li si" + i);
			map.put("phone", "113456" + i);
			data.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.listview_item, new String[] { "name", "phone" },
				new int[] { R.id.txtPname, R.id.txtPno });
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final TextView idt = (TextView) view.findViewById(R.id.txtPno);
				Intent result = new Intent();
				result.putExtra("phoneno", idt.getText().toString());
				// Log.i("ZENG", idt.getText().toString());
				setResult(RESULT_OK, result);
				finish();

			}

		});

	}

}
