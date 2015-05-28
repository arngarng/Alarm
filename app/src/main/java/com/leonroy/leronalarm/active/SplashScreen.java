package com.leonroy.leronalarm.active;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.leonroy.leronalarm.R;
import com.leonroy.leronalarm.service.DownloadService;
import com.leonroy.leronalarm.utils.MsgUtil;
import com.leonroy.leronalarm.utils.ValidateUtil;

/**
 * project : LeRonAlarm
 * Created by arng on 2015/5/26.
 */
public class SplashScreen extends Activity {
    private boolean timeoutFlag = false;
    private ProgressDialog progressDialog = null;
    private DownloadService service = DownloadService.getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalshscreen);
        //判斷sdk版本
        if (!ValidateUtil.isAndroid4(this)) {
            AlertDialog.Builder dialog = MsgUtil.alert(this, R.string.version_error);
            dialog.setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                }
            });
            dialog.show();
        }
        //檢查table是否有日期資料
        if (!ValidateUtil.existsData(this)) {
            //檢查是否有網路連線
            if(!ValidateUtil.isConnectNet(this)) {
                MsgUtil.toast(this, R.string.net_error);
                intoMainActivity();
            } else {
                //執行資料下載
                processDownload();
            }
        }
    }

    private void processDownload() {
        progressDialog = ProgressDialog.show(SplashScreen.this
                , getResources().getString(R.string.watting)
                , getResources().getString(R.string.downloading), true);
        handler.postDelayed(timeoutRunnable,
                getResources().getInteger(R.integer.connet_timeout) * 1000);
        Thread downloadThread = new Thread(downloadRunnable);
        downloadThread.start();
    }

    /**
     * Handler宣告
     */
    private Handler handler = new Handler() {
        //覆寫Message處理方法
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String msgString = (String) msg.obj;
            if ("download".equals(msgString)) {
                this.removeCallbacks(timeoutRunnable);
            }
        }
    };

    //設定連線TIMEOUT
    private Runnable timeoutRunnable = new Runnable() {
        @Override
        public void run() {
            //停止下載Thread
            timeoutFlag = true;
            //關閉進度bar
            progressDialog.dismiss();
            //蹦現連線逾時視窗
            AlertDialog.Builder alert = MsgUtil.alert(SplashScreen.this, R.string.timeout_error);
            alert.setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    //進入主畫面
                    intoMainActivity();
                }
            });
            alert.show();
        }
    };

    //設定假日資料下載
    private Runnable downloadRunnable = new Runnable() {
        @Override
        public void run() {
            //下載資料
            service.downloadData(SplashScreen.this);
            if (!timeoutFlag) {
                //傳遞訊息,供handler判斷
                handler.sendMessage(handler.obtainMessage(1, "download"));
                //關閉進度bar
                progressDialog.dismiss();
                intoMainActivity();
            }
        }};

    /**
     * 進入主功能頁面
     */
    private void intoMainActivity() {
        Log.i("IFNO","閉閉splashScreen");
        //進入主畫面
        Intent i = new Intent(SplashScreen.this, Alarm.class);
        startActivity(i);
        finish();
    }
}
