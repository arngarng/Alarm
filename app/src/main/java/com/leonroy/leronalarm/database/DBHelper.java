package com.leonroy.leronalarm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leonroy.leronalarm.database.dao.AlarmDAO;
import com.leonroy.leronalarm.database.dao.SystemDAO;
import com.leonroy.leronalarm.database.dao.VacationDAO;

/**
 * Created by arng on 2015/5/25.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "leonroy.db";
    private static SQLiteDatabase mInstance = null;

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (mInstance == null || !mInstance.isOpen()) {
            mInstance = new DBHelper(context).getWritableDatabase();
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /**建立table*/
        //alarm table
        db.execSQL(AlarmDAO.CREATE_TABLE);
        //vacation table
        db.execSQL(VacationDAO.CREATE_TABLE);
        //system table
        db.execSQL(SystemDAO.CREATE_TABLE);
        /**匯入初始資料*/
        db.execSQL(SystemDAO.INSERT_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    /*
            if (newVersion > oldVersion) {
                for (int i = oldVersion ; i < newVersion ; i ++) {

                    db.beginTransaction();// 建立交易

                    boolean success = false;// 判斷參數

                    // 由之前不用的版本，可做不同的動作
                    switch (oldVersion) {
                        case 1:

                            success = true;
                            break;
                    }

                    if (success) {
                        db.setTransactionSuccessful();// 正確交易才成功
                    }
                    db.endTransaction();
                }
            } else {
                onCreate(db);
            }
        */
    }
}
