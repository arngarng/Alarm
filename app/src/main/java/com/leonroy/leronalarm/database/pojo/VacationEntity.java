package com.leonroy.leronalarm.database.pojo;

/**
 * Created by arng on 2015/5/25.
 */
public class VacationEntity {
    /** id*/
    private long id = 0L;
    /** 假日日期*/
    private String date = "";
    /** 假日說明*/
    private String desc ="";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
