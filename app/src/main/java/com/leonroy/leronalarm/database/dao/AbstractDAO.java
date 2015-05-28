package com.leonroy.leronalarm.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leonroy.leronalarm.database.DBHelper;

/**
 * project : LeRonAlarm
 * Created by arng on 2015/5/25.
 */
public abstract class AbstractDAO<T> {

    public static final String KEY_ID = "_id";
    private SQLiteDatabase db = null;
    //需實作的method
    abstract protected T getRecord(Cursor cursor);
    //constructors
    public AbstractDAO(Context context) {
        db = DBHelper.getInstance(context);
    }

    public void close() {
        db.close();
    }

    public long insert(String tableName, ContentValues cv) {
        return db.insert(tableName, null, cv);
    }

    // 刪除參數指定編號的資料
    public boolean delete(String tableName, long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(tableName, where, null) > 0;
    }

    public boolean update(String tableName, ContentValues cv, long id) {
        String where = KEY_ID + "=" + id;
        return db.update(tableName, cv, where, null) > 0;
    }

    public Cursor getAll(String tableName) {
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor get(String tableName, long id) {
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(tableName, null, where, null, null, null, null);
        return cursor;
    }
    // 取得資料數量
    public int getCount(String tableName) {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }

    public SQLiteDatabase getDB() {
        return db;
    }
}
