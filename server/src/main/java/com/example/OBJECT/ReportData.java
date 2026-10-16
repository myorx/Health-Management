package com.example.OBJECT;

import java.io.Serializable;

public class ReportData implements Serializable {
    int dayNumber;//记录总天数
    int MostNumber;//记录最多的月份记录的天数
    int MostNumberMonth;//上面那行对应的月份
    int LeastNumber;//同理这是记录最少的
    int LeastNumberMonth;
    int HighestHeight;
    int LeastHeight;
    int HighestWeight;
    int LeastWeight;
    int MostStep;
    int MostStepMonth;
    int MostStepDay;
    int AverageSleepHours;

    public ReportData(int dayNumber, int MostNumberMonth, int MostNumber, int LeastNumberMonth, int LeastNumber, int HighestHeight, int LeastHeight, int HighestWeight, int LeastWeight, int MostStepMonth, int MostStepDay, int MostStep, int AverageSleepHours) {
        this.AverageSleepHours = AverageSleepHours;
        this.HighestHeight = HighestHeight;
        this.MostStep = MostStep;
        this.MostNumberMonth = MostNumberMonth;
        this.MostNumber = MostNumber;
        this.dayNumber = dayNumber;
        this.HighestWeight = HighestWeight;
        this.LeastHeight = LeastHeight;
        this.LeastNumber = LeastNumber;
        this.LeastNumberMonth = LeastNumberMonth;
        this.LeastWeight = LeastWeight;
        this.MostStepDay = MostStepDay;
        this.MostStepMonth = MostStepMonth;
    }

    public int getAverageSleepHours() {
        return AverageSleepHours;
    }

    public int getHighestHeight() {
        return HighestHeight;
    }

    public int getHighestWeight() {
        return HighestWeight;
    }

    public int getLeastHeight() {
        return LeastHeight;
    }

    public int getLeastWeight() {
        return LeastWeight;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getLeastNumber() {
        return LeastNumber;
    }

    public int getLeastNumberMonth() {
        return LeastNumberMonth;
    }

    public int getMostNumber() {
        return MostNumber;
    }

    public int getMostNumberMonth() {
        return MostNumberMonth;
    }

    public int getMostStep() {
        return MostStep;
    }

    public int getMostStepDay() {
        return MostStepDay;
    }

    public int getMostStepMonth() {
        return MostStepMonth;
    }

}
