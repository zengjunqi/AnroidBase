package com.zengyan.androidbase.test;

import java.util.List;

import com.zengyan.androidbase.model.Person;
import com.zengyan.androidbase.services.SqliteDBOperator;
import com.zengyan.androidbase.services.SqliteHelper;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestClass extends AndroidTestCase {

	public void testdbcreate() {

		SqliteHelper helper = new SqliteHelper(getContext());
		helper.getReadableDatabase();

	}

	public void TestInsert() {

		SqliteDBOperator dbOperator = new SqliteDBOperator(getContext());
		dbOperator.Insert(new Person(0, "zbbya", "678836987"));
	}

	public void TestDelete() {

		SqliteDBOperator dbOperator = new SqliteDBOperator(getContext());
		dbOperator.Delete(1);
	}

	public void TestUpdate() {

		SqliteDBOperator dbOperator = new SqliteDBOperator(getContext());
		dbOperator.Update(new Person(2, "aaa", "123456789"));
	}

	public void TestQueryAll() {

		SqliteDBOperator dbOperator = new SqliteDBOperator(getContext());
		List<Person> aList = dbOperator.QueryAll();
		for (Person person : aList) {
			Log.i("zyj", person.toString());
		}

	}

	public void TestQueryItem() {

		SqliteDBOperator dbOperator = new SqliteDBOperator(getContext());
		Person person = dbOperator.QueryItemById(2);
		Log.i("zyj", person.toString());
	}
}
