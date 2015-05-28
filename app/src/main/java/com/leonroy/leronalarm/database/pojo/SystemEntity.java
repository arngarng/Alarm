package com.leonroy.leronalarm.database.pojo;

/**
 * Created by arng on 2015/5/25.
 */
public class SystemEntity {
    /** 假日資料版本*/
    private String verison = "";
    /** 假日資料年份*/
    private String year = "";
    /** 假日資料URL*/
    private String url = "";

    public String getVerison() {
        return verison;
    }

    public void setVerison(String verison) {
        this.verison = verison;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
