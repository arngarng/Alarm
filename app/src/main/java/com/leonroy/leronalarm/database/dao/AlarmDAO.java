package com.leonroy.leronalarm.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.leonroy.leronalarm.database.pojo.AlarmEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arng on 2015/5/25.
 */
public class AlarmDAO extends AbstractDAO<AlarmEntity> {
    //db name
    public static final String TABLE_NAME = "alarm";
    //column name
    public static final String TIME_COLUMN = "time";
    public static final String SOUND_COLUMN = "sound";
    public static final String VIBER_COLUMN = "viber";
    public static final String DESC_COLUMN = "desc";
    public static final String PERIOD_COLUMN = "period";
    public static final String HOLIDAY_COLUMN = "holiday";

    //create table sql
    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS '%S' (" +
            "%S INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%S TEXT NOT NULL, %S TEXT NOT NULL, %S TEXT NOT NULL, " +
            "%S TEXT NOT NULL, %S TEXT NOT NULL, %S TEXT NOT NULL)"
            ,TABLE_NAME,KEY_ID,TIME_COLUMN,SOUND_COLUMN,VIBER_COLUMN,DESC_COLUMN,PERIOD_COLUMN,HOLIDAY_COLUMN);

    //constructors
    public AlarmDAO(Context context) {
        super(context);
    }
    // 新增參數指定的物件
    public AlarmEntity insert(AlarmEntity alarm) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(TIME_COLUMN,alarm.getTime());
        cv.put(SOUND_COLUMN,alarm.getSound());
        cv.put(DESC_COLUMN,alarm.getDesc());
        cv.put(VIBER_COLUMN,alarm.getViber());
        cv.put(HOLIDAY_COLUMN,alarm.getHoliday());
        cv.put(PERIOD_COLUMN,alarm.getPeriod());
        //insert資料至DB
        long id = insert(TABLE_NAME, cv);
        //將取得的id設定回entity,並回傳
        alarm.setId(id);
        return alarm;
    }
    // 刪除參數指定的物件
    public boolean delete(long id) {
        return delete(TABLE_NAME, id);
    }

    // 更新參數指定的物件
    public boolean update(AlarmEntity alarm) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(TIME_COLUMN,alarm.getTime());
        cv.put(SOUND_COLUMN,alarm.getSound());
        cv.put(DESC_COLUMN,alarm.getDesc());
        cv.put(VIBER_COLUMN,alarm.getViber());
        cv.put(HOLIDAY_COLUMN,alarm.getHoliday());
        cv.put(PERIOD_COLUMN,alarm.getPeriod());

        return update(TABLE_NAME, cv, alarm.getId());
    }
    //查詢
    public List<AlarmEntity> getAll() {
        List<AlarmEntity> list = new ArrayList<AlarmEntity>();
        Cursor cursor = getAll(TABLE_NAME);
        while (cursor.moveToNext()) {
            list.add(getRecord(cursor));
        }
        cursor.close();
        return list;
    }
    //查詢by ID
    public AlarmEntity get(long id) {
        AlarmEntity entity = null;
        Cursor cursor = get(TABLE_NAME, id);
        if (cursor.moveToNext()) {
            entity = getRecord(cursor);
        }
        cursor.close();
        return entity;
    }

    @Override
    protected AlarmEntity getRecord(Cursor cursor) {
        AlarmEntity entity = new AlarmEntity();
        entity.setId(cursor.getLong(0));
        entity.setTime(cursor.getString(1));
        entity.setSound(cursor.getString(2));
        entity.setViber(cursor.getString(3));
        entity.setDesc(cursor.getString(4));
        entity.setPeriod(cursor.getString(5));
        entity.setHoliday(cursor.getString(6));
        return entity;
    }
}
