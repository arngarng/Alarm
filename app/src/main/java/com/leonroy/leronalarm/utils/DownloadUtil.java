package com.leonroy.leronalarm.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import com.leonroy.leronalarm.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by arng on 2015/5/26.
 */
public class DownloadUtil {
    /**
     * 根據URL得到輸入流
     */
    public static InputStream getInputStreamFromUrl(String urlStr) throws MalformedURLException, IOException
    {
        // 建立一個URL物件
        URL url = new URL(urlStr);
        // 建立一個Http連接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        //得到輸入流
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }
}
