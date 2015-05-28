package com.leonroy.leronalarm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;

import com.leonroy.leronalarm.R;
import com.leonroy.leronalarm.database.dao.VacationDAO;
import com.leonroy.leronalarm.database.pojo.VacationEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by arng on 2015/5/26.
 */
public class ValidateUtil {

    /**
     *  檢查網路連線
     * @param context
     * @return 網路是否有連線
     */
    public static boolean isConnectNet(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        //For Data network check
        boolean is3g =
                manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        //For WiFi Check
        boolean isWifi =
                manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        Log.i("INFO","3G: " + is3g + ", WIFI: " + isWifi);
        return is3g||isWifi;
    }

    /**
     * 檢查Android版本
     * @param context
     * @return 版本是否符合4.0以上
     */
    public static boolean isAndroid4(Context context) {
        int sdk = Integer.parseInt(Build.VERSION.SDK);
        Log.i("INFO","SDK: " + sdk);
        return sdk >= context.getResources().getInteger(R.integer.sdk_version);
    }

    /**
     * 判斷是否存在假日資料
     * @param context
     * @return 假日資料是否存在
     */
    public static boolean existsData(Context context) {
        VacationDAO dao = new VacationDAO(context);
        String year = DateUtil.getFormat(DateUtil.YEAR).format(new Date());
        List<VacationEntity> list = dao.getByYear(year);
        Log.i("INFO",year + " vacation days : " + list.size());
        return list.size() > 0;
    }
}
