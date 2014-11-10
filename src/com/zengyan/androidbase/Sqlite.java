package com.zengyan.androidbase;

import java.sql.SQLXML;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zengyan.androidbase.model.Person;
import com.zengyan.androidbase.services.SqliteDBOperator;

public class Sqlite extends Activity {

	ListView lv;
	List<Person> ps;
	LayoutInflater inflater;
	private SqliteDBOperator dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteactivity);
		inflater = this.getLayoutInflater();
		lv = (ListView) findViewById(R.id.lvsqlite);
		dbHelper = new SqliteDBOperator(this);
		refreshUI();

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final TextView idt = (TextView) view.findViewById(R.id.txtPid);
				setSingleSelectDialog(Integer
						.parseInt(idt.getText().toString()));
				return false;
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, 0, 0, "添加新联系人");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case 0:
			Intent it = new Intent(Sqlite.this, SqliteUpdate.class);
			it.putExtra("oper", "add");
			startActivity(it);

			break;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		refreshUI();
		super.onResume();
	
	}

	private void refreshUI() {
		ps = dbHelper.QueryAll();
		lv.setAdapter(new MyAdapter());
	}

	private void setSingleSelectDialog(final int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Please select operator");
		builder.setSingleChoiceItems(new String[] { "Update", "Delete" }, -1,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						System.out.println(id + "******id");
						if (which == 0) {
							Intent it = new Intent(Sqlite.this,
									SqliteUpdate.class);
							it.putExtra("id", id);
							it.putExtra("oper", "update");
							startActivity(it);
						} else if (which == 1) {

							SqliteDBOperator helper = new SqliteDBOperator(
									Sqlite.this);
							helper.Delete(id);
							refreshUI();
							Toast.makeText(Sqlite.this, "Delete success!", 2000)
									.show();
						}

					}

				});


		builder.create().show();

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub

			return ps.size();//当数据库一条记录没有时,会报null异常
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return ps.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ViewHolder vh = null;

			if (convertView == null) {

				vh = new ViewHolder();

				convertView = inflater.inflate(R.layout.listview_item, null);
				vh.tid = (TextView) convertView.findViewById(R.id.txtPid);
				vh.tname = (TextView) convertView.findViewById(R.id.txtPname);
				vh.tphoneno = (TextView) convertView.findViewById(R.id.txtPno);

				convertView.setTag(vh);

			} else {

				vh = (ViewHolder) convertView.getTag();

			}
			vh.tid.setText(String.valueOf(ps.get(position).getId()));// setText必须给的String的值
			vh.tname.setText(ps.get(position).getName());
			vh.tphoneno.setText(ps.get(position).getPhoneno());

			return convertView;

		}

	}

	static class ViewHolder {
		TextView tid, tname, tphoneno;
	}

}
