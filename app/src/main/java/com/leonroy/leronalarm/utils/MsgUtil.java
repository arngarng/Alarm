package com.leonroy.leronalarm.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.leonroy.leronalarm.R;

/**
 * Created by arng on 2015/5/26.
 */
public class MsgUtil {
    /**
    * 蹦出訊息視窗
    * @param context activity物件
    * @param msg R.string.xxx (xml設定的值)
    */
    public static void toast(Context context, int msg) {
        Toast.makeText(context
                , context.getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    /**
    * 蹦出訊息視窗
    * @param context activity物件
    * @param msg 訊息
    */
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 蹦出訊息確認視窗
     * @param context activity物件
     * @param msg 訊息
     */
    public static AlertDialog.Builder alert(Context context, int msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(context.getResources().getString(msg));
        return alertDialog;
    }
}
