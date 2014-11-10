package com.zengyan.androidbase.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.zengyan.androidbase.services.SqliteHelper;

public class MyContactProvider extends ContentProvider {

	private static final String AUTHORITY = "com.zengyan.androidbase.provider.MyContactProvider";
	private static final int CONTACT_INSERT_CODE = 0; // 操作contact表添加的操作的uri匹配码
	private static final int CONTACT_DELETE_CODE = 1;
	private static final int CONTACT_UPDATE_CODE = 2;
	private static final int CONTACT_QUERY_ALL_CODE = 3;
	private static final int CONTACT_QUERY_ITEM_CODE = 4;

	private static UriMatcher uriMatcher;
	private SqliteHelper mOpenHelper; // contact表的数据库帮助对象

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		// 添加一些uri(分机号)

		// com.zengyan.androidbase.provider.MyContactProvider/contact/insert
		uriMatcher.addURI(AUTHORITY, "contact/insert", CONTACT_INSERT_CODE);

		// com.zengyan.androidbase.provider.MyContactProvider/contact/delete
		uriMatcher.addURI(AUTHORITY, "contact/delete", CONTACT_DELETE_CODE);


		// com.zengyan.androidbase.provider.MyContactProvider/contact/update
		uriMatcher.addURI(AUTHORITY, "contact/update", CONTACT_UPDATE_CODE);

		// com.zengyan.androidbase.provider.MyContactProvider/contact/queryAll
		uriMatcher.addURI(AUTHORITY, "contact/queryAll", CONTACT_QUERY_ALL_CODE);

		// com.zengyan.androidbase.provider.MyContactProvider/contact/query/#
		uriMatcher.addURI(AUTHORITY, "contact/query/#", CONTACT_QUERY_ITEM_CODE);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mOpenHelper = new SqliteHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		switch (uriMatcher.match(uri)) {
		case CONTACT_QUERY_ALL_CODE:
			if (db.isOpen()) {
				Cursor cursor = db.query("contact", projection, selection,
						selectionArgs, null, null, sortOrder);
				return cursor;
			}
			;
			break;
		case CONTACT_QUERY_ITEM_CODE:
			if (db.isOpen()) {
				long id = ContentUris.parseId(uri);

				Cursor cursor = db.query("contact", projection, "id = ?",
						new String[] { id + "" }, null, null, sortOrder);

				return cursor;
			}
			break;
		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}

		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case CONTACT_QUERY_ALL_CODE: // 返回多条的MIME-type
			return "vnd.android.cursor.dir/contact";
		case CONTACT_QUERY_ITEM_CODE: // 返回单条的MIME-TYPE
			return "vnd.android.cursor.item/contact";
		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case CONTACT_INSERT_CODE:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				long id = db.insert("contact", null, values);
				db.close();
				return ContentUris.withAppendedId(uri, id);
			}

			break;

		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case CONTACT_DELETE_CODE:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				int count = db.delete("contact", selection, selectionArgs);
				db.close();
				 getContext().getContentResolver().notifyChange(uri, null);
				return count;
			}

			break;

		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case CONTACT_UPDATE_CODE:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				int count = db.update("contact",values, selection, selectionArgs);
				db.close();
				return count;
			}

			break;

		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return 0;
	}

}
