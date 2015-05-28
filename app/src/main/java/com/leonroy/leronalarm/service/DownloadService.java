package com.leonroy.leronalarm.service;

import android.content.Context;
import android.util.Log;

import com.leonroy.leronalarm.R;
import com.leonroy.leronalarm.database.pojo.SystemEntity;
import com.leonroy.leronalarm.utils.DownloadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by arng on 2015/5/26.
 */
public class DownloadService {

    /** 下載service*/
    private static DownloadService service;

    /**
     * 取得Service
     * @return
     */
    public static DownloadService getService() {
        if (service == null) {
            service = new DownloadService();
        }
        return service;
    }

    /**
     * 下載假日資料
     * @param context
     */
    public void downloadData(final Context context) {
        //取得設定資料
        String setting = downloadSetting(context);
        if (setting != null) {
            //[0] version ; [1] year ; [2] url
            String[] settings = setting.split(",");
            SystemEntity sysEntity = new SystemEntity();
            sysEntity.setVerison(settings[0]);
            sysEntity.setYear(settings[1]);
            sysEntity.setUrl(settings[2]);
            Log.i("INFO",setting);
            String data = downloadVacationData(context,settings[2]);
            Log.i("INFO",data);
        }
    }

    /**
     *
     * @param context
     * @return
     */
    private String downloadSetting(Context context) {
        BufferedReader br = null;
        String str = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                                    DownloadUtil.getInputStreamFromUrl(context.getResources().getString(R.string.url))));
            str = br.readLine();
        } catch (IOException e) {
            Log.e("ERROR",e.getCause() + "-" + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                Log.e("ERROR", e.getCause() + "-" + e.getMessage());
            }
        }
        return str;
    }

    private String downloadVacationData(Context context,String url) {
        BufferedReader br = null;
        String str = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            DownloadUtil.getInputStreamFromUrl(url)));
            str = br.readLine();
        } catch (IOException e) {
            Log.e("ERROR",e.getCause() + "-" + e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                Log.e("ERROR", e.getCause() + "-" + e.getMessage());
            }
        }
        return str;
    }
}
