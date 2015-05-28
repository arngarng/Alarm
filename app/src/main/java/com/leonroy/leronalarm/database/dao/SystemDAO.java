package com.leonroy.leronalarm.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.leonroy.leronalarm.database.pojo.SystemEntity;

/**
 * Created by arng on 2015/5/25.
 */
public class SystemDAO extends AbstractDAO<SystemEntity> {
    //db name
    public static final String TABLE_NAME = "system";
    //column name
    public static final String VERSION_COLUMN = "ver";
    public static final String YEAR_COLUMN = "year";
    public static final String URL_COLUMN = "url";

    //create table sql
    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS '%S' (%S TEXT NOT NULL," +
            "%S TEXT NOT NULL, %S TEXT NOT NULL)"
            ,TABLE_NAME,VERSION_COLUMN,YEAR_COLUMN,URL_COLUMN);
    //insert initial data sql
    public static final String INSERT_DATA = String.format("INSERT INTO %S VALUES('%S','%S','%S')", TABLE_NAME, "0","","");

    //constructors
    public SystemDAO(Context context) {
        super(context);
    }

    public boolean update(SystemEntity systemEntity) {
        ContentValues cv = new ContentValues();
        cv.put("ver", systemEntity.getVerison());
        cv.put("year", systemEntity.getYear());
        cv.put("url", systemEntity.getUrl());
        return getDB().update(TABLE_NAME,cv,null,null) > 0;
    }

    public SystemEntity get() {
        SystemEntity entity = null;
        Cursor cursor = getDB().query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            entity = getRecord(cursor);
        }
        cursor.close();
        return entity;
    }

    @Override
    protected SystemEntity getRecord(Cursor cursor) {
        SystemEntity entity = new SystemEntity();
        entity.setVerison(cursor.getString(0));
        entity.setYear(cursor.getString(1));
        entity.setUrl(cursor.getString(2));
        return entity;
    }
}
