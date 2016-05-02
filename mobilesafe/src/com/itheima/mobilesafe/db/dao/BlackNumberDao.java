package com.itheima.mobilesafe.db.dao;

import java.util.ArrayList;

import com.itheima.mobilesafe.classDemo.BlackNumber;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BlackNumberDao {

	private static BlackNumberDao sInstance = null;
	private BlackNumberOpenHelper openHelper;

	private BlackNumberDao(Context cxt) {
		openHelper = new BlackNumberOpenHelper(cxt);
	};

	public static BlackNumberDao getInstance(Context cxt) {

		if (sInstance == null) {
			synchronized ("0") {
				if (sInstance == null) {
					sInstance = new BlackNumberDao(cxt);
				}

			}

		}

		return sInstance;
	}

	/**
	 * 增加黑名单
	 */
	public void add(String number, int mode) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", number);
		values.put("mode", mode);
		db.insert("blacknumber", null, values);
		values.clear();
		db.close();
	}

	/**
	 * 删除数据
	 */
	public void delete(String number) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.delete("blacknumber", "number=?", new String[] { number });

	}

	/**
	 * 更改数据
	 */
	public void update(String number, int mode) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("mode", mode);
		db.update("blacknumber", values, "number=?", new String[] { number });
		db.close();

	}

	/**
	 * 查询是否存在
	 */
	public boolean find(String number) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		boolean exist = false;
		Cursor cursor = db.query("blacknumber", new String[] { "mode" },
				"number=?", new String[] { number }, null, null, null);
		if (cursor.moveToFirst()) {
			exist = true;
		}
		cursor.close();
		db.close();

		return exist;

	}

	/**
	 * 查询模式
	 */
	public int findMode(String number) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int mode = -1;
		Cursor cursor = db.query("blacknumber", new String[] { "mode" },
				"number=?", new String[] { number }, null, null, null);
		if (cursor.moveToFirst()) {
			mode = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		
		return mode;
	}
	/**
	 * 查询所有
	 */
	public ArrayList<BlackNumber> findAll() {
		ArrayList list=new ArrayList<BlackNumber>();
		SQLiteDatabase db = openHelper.getWritableDatabase();
		Cursor cursor = db.query("blacknumber", new String[] {"number","mode"},
				null,null, null, null, null);
		while(cursor.moveToNext()){
			BlackNumber blackNumber=new BlackNumber(null, 0);
			String number = cursor.getString(0);
			int mode=cursor.getInt(1);
			blackNumber.setNumber(number);
			blackNumber.setMode(mode);
			list.add(blackNumber);
		}
		cursor.close();
		db.close();
		return list;
	}
}
