package com.leonroy.leronalarm.database.pojo;

/**
 * Created by arng on 2015/5/25.
 */
public class AlarmEntity {
    /** id*/
    private long id = 0L;
    /** 鬧鐘時間*/
    private String time = "";
    /** 鬧鐘鈴聲*/
    private String sound = "";
    /** 鬧鐘震動*/
    private String viber = "";
    /** 鬧鐘說明*/
    private String desc = "";
    /** 鬧鐘週期*/
    private String period = "";
    /** 鬧鐘假日是否提醒*/
    private String holiday = "";

    @Override
    public String toString() {
        return "AlarmEntity{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", sound='" + sound + '\'' +
                ", viber='" + viber + '\'' +
                ", desc='" + desc + '\'' +
                ", period='" + period + '\'' +
                ", holiday='" + holiday + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getViber() {
        return viber;
    }

    public void setViber(String viber) {
        this.viber = viber;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
}
