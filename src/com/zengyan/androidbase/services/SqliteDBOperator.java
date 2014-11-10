package com.zengyan.androidbase.services;

import java.util.ArrayList;
import java.util.List;

import com.zengyan.androidbase.model.Person;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SqliteDBOperator {

	SqliteHelper dbHelper;

	public SqliteDBOperator(Context context) {
		dbHelper = new SqliteHelper(context);
	}

	public void Insert(Person person) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("insert into contact(name, phoneno) values(?,?)",
					new Object[] { person.getName(), person.getPhoneno() });

			db.close();
		}
	}

	public void Delete(int id) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("delete from contact where id=?", new Integer[] { id });

			db.close();
		}
	}

	public void Update(Person person) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("update contact set name=?, phoneno=? where id=?",
					new Object[] { person.getName(), person.getPhoneno(),
							person.getId() });

			db.close();
		}
	}

	public List<Person> QueryAll() {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery("select * from contact", null);
			if (cursor != null && cursor.getCount() > 0) {
				int id;
				String name, phoneno;
				while (cursor.moveToNext()) {
					id = cursor.getInt(cursor.getColumnIndex("id"));
					name = cursor.getString(cursor.getColumnIndex("name"));
					phoneno = cursor
							.getString(cursor.getColumnIndex("phoneno"));

					persons.add(new Person(id, name, phoneno));

				}
				cursor.close();
				db.close();
				return persons;
			}

			db.close();
		}

		return null;
	}

	public Person QueryItemById(int id) {

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery("select * from contact where id=?",
					new String[] { id + "" });
			if (cursor != null && cursor.getCount() > 0) {

				while (cursor.moveToNext()) {
					int _id = cursor.getInt(cursor.getColumnIndex("id"));
					String name = cursor.getString(cursor
							.getColumnIndex("name"));
					String phoneno = cursor.getString(cursor
							.getColumnIndex("phoneno"));
					cursor.close();
					db.close();
					return new Person(_id, name, phoneno);

				}
			}

			db.close();
		}

		return null;
	}

	public void Trans(Person person) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db.isOpen()) {
			try {
				db.beginTransaction();
				db.execSQL("update contact set name=?, phoneno=? where id=?",
						new Object[] { person.getName(), person.getPhoneno(),
								person.getId() });
				db.execSQL(
						"update contact set name=?, phoneno=? where id=?",
						new Object[] { "secord", person.getPhoneno(),
								person.getId() });
				db.setTransactionSuccessful();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.endTransaction();
			}
			db.close();
		}
	}

}
