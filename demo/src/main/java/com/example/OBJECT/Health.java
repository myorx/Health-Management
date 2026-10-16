package com.example.OBJECT;

import java.io.*;

public class Health implements Serializable {
    public String user;
    public String Date;
    public String year;
    public String month;
    public String date;
    public String height;
    public String step;
    public String sleep;
    public String weight;

    public Health(String user, String year, String month, String date, String height, String step, String sleep, String weight) {
        this.user = user;
        this.year = year;
        this.month = month;
        this.date = date;
        this.height = height;
        this.step = step;
        this.sleep = sleep;
        this.weight = weight;
        this.Date = year + "年" + month + "月" + date + "日";
    }
}
