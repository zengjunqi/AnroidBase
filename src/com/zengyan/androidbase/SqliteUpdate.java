package com.zengyan.androidbase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zengyan.androidbase.model.Person;
import com.zengyan.androidbase.services.SqliteDBOperator;

public class SqliteUpdate extends Activity {

	Person person;
	SqliteDBOperator helper;
	EditText tname, tphone;
	TextView ttile;
	Button btnSave;
	ImageView iv;
	private String oper;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sqliteaddactivity);
		ttile = (TextView) findViewById(R.id.txtTitle);
		tname = (EditText) findViewById(R.id.name);
		tphone = (EditText) findViewById(R.id.phoneno);
		btnSave = (Button) findViewById(R.id.btnsqlSave);
		iv = (ImageView) findViewById(R.id.imgback);
		helper = new SqliteDBOperator(this);
		Intent it = this.getIntent();
		id = it.getIntExtra("id", 0);
		oper = it.getStringExtra("oper");
		if (oper.equals("update")) {
			ttile.setText("更新联系人");
			person = helper.QueryItemById(id);
			tname.setText(person.getName());
			tphone.setText(person.getPhoneno());
		}
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SqliteUpdate.this.finish();
			}
		});
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (oper.equals("update")) {
					person.setName(tname.getText().toString());
					person.setPhoneno(tphone.getText().toString());
					person.setId(id);
					helper.Update(person);
					Toast.makeText(SqliteUpdate.this, "Update success!", 2000)
							.show();
					SqliteUpdate.this.finish();
				} else if (oper.equals("add")) {
					person = new Person();
					person.setName(tname.getText().toString());
					person.setPhoneno(tphone.getText().toString());
					helper.Insert(person);
					Toast.makeText(SqliteUpdate.this, "Add access!", 2000)
							.show();
					SqliteUpdate.this.finish();
				}
			}
		});
	}

}
