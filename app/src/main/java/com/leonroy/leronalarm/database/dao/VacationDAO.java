package com.leonroy.leronalarm.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.leonroy.leronalarm.database.pojo.VacationEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arng on 2015/5/25.
 */
public class VacationDAO extends AbstractDAO<VacationEntity> {
    //db name
    public static final String TABLE_NAME = "vacation";
    //column name
    public static final String DATE_COLUMN = "date";
    public static final String DESC_COLUMN = "desc";
    //create table sql
    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS '%S' (" +
            "%S INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%S TEXT NOT NULL, %S TEXT NOT NULL)",
            TABLE_NAME,KEY_ID,DATE_COLUMN,DESC_COLUMN);

    //constructors
    public VacationDAO(Context context) {
       super(context);
    }

    // 新增參數指定的物件
    public VacationEntity insert(VacationEntity vacation) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATE_COLUMN,vacation.getDate());
        cv.put(DESC_COLUMN,vacation.getDesc());
        //insert資料至DB
        long id = insert(TABLE_NAME, cv);
        //將取得的id設定回entity,並回傳
        vacation.setId(id);
        return vacation;
    }
    // 刪除參數指定的物件
    public boolean delete(long id) {
        return delete(TABLE_NAME, id);
    }

    // 更新參數指定的物件
    public boolean update(VacationEntity vacation) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATE_COLUMN,vacation.getDate());
        cv.put(DESC_COLUMN, vacation.getDesc());

        return update(TABLE_NAME, cv, vacation.getId());
    }
    //查詢
    public List<VacationEntity> getAll() {
        List<VacationEntity> list = new ArrayList<VacationEntity>();
        Cursor cursor = getAll(TABLE_NAME);
        while (cursor.moveToNext()) {
            list.add(getRecord(cursor));
        }
        cursor.close();
        return list;
    }

    //查詢
    public List<VacationEntity> getByYear(String year) {
        List<VacationEntity> list = new ArrayList<VacationEntity>();
        String where = "substr(" + DATE_COLUMN + ",1,4) = " + year;
        Cursor cursor = getDB().query(TABLE_NAME,null,where,null,null,null,null);
        while (cursor.moveToNext()) {
            list.add(getRecord(cursor));
        }
        cursor.close();
        return list;
    }

    //查詢by ID
    public VacationEntity get(long id) {
        VacationEntity entity = null;
        Cursor cursor = get(TABLE_NAME, id);
        if (cursor.moveToNext()) {
            entity = getRecord(cursor);
        }
        cursor.close();
        return entity;
    }
    //取得資料筆數
    public int getCount() {
        return super.getCount(TABLE_NAME);
    }

    @Override
    protected VacationEntity getRecord(Cursor cursor) {
        VacationEntity entity = new VacationEntity();
        entity.setId(cursor.getLong(0));
        entity.setDate(cursor.getString(1));
        entity.setDesc(cursor.getString(2));
        return entity;
    }
}
