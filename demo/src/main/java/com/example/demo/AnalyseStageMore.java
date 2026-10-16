package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Calendar;

public class AnalyseStageMore {

    @FXML
    private Button BMI;

    @FXML
    private Pane basicPane;



    @FXML
    private TextField month;

    @FXML
    private Button sleepTime;

    @FXML
    private Button step;

    @FXML
    private Button weight;

    @FXML
    private TextField year;
    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void onBMI(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String yearData = year.getText();
        String monthData = month.getText();
        if (yearData.equals("") || monthData.equals("")) {
            return;
        }
        Main.connect.sendMessage("BMIMonthRequest");
        Main.connect.sendMessage(yearData);
        Main.connect.sendMessage(monthData);
        int days=getMaxDay(yearData,monthData);
        xAxis = new NumberAxis(1, days, 1);
        yAxis = new NumberAxis(0, 40, 1);
        LineChart lineChart = new LineChart(xAxis, yAxis);

        int[] BMI = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("BMI");
        xAxis.setLabel("月份");
        lineChart.setTitle("BMI变化");
        series.setName("BMI");
        for (int i = 1; i <= days; ++i) {
            series.getData().add(new XYChart.Data(i, BMI[i]));
            System.out.println(BMI[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }
    @FXML
    void onSleep(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String yearData = year.getText();
        String monthData =month.getText();
        if (yearData.equals("") || monthData.equals("")) {
            return;
        }
        Main.connect.sendMessage("SleepMonthRequest");
        Main.connect.sendMessage(yearData);
        Main.connect.sendMessage(monthData);
        int days=getMaxDay(yearData,monthData);
        xAxis = new NumberAxis(1, days, 1);
        this.yAxis = new NumberAxis(0, 24, 1);
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        int[] sleep = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("睡眠时长/h");
        xAxis.setLabel("月份");
        lineChart.setTitle("睡眠变化");
        series.setName("睡眠");
        for (int i = 1; i <= days; ++i) {
            series.getData().add(new XYChart.Data(i, sleep[i]));
            System.out.println(sleep[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }

    @FXML
    void onStep(ActionEvent event) throws Exception {
        String yearData = year.getText();
        String monthData =month.getText();
        if (yearData.equals("") || monthData.equals("")) {
            return;
        }
        Main.connect.sendMessage("StepMonthRequest");
        Main.connect.sendMessage(yearData);
        Main.connect.sendMessage(monthData);
        int days=getMaxDay(yearData,monthData);
        xAxis = new NumberAxis(1, days, 1);
        this.yAxis = new NumberAxis(0, 50000, 1000);
        basicPane.getChildren().clear();
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        int[] step = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("步数");
        xAxis.setLabel("月份");
        lineChart.setTitle("步数");
        series.setName("步数");
        for (int i = 1; i <= days; ++i) {
            series.getData().add(new XYChart.Data(i, step[i]));
            System.out.println(step[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }

    @FXML
    void onWeight(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String yearData = year.getText();
        String monthData =month.getText();
        if (yearData.equals("") || monthData.equals("")) {
            return;
        }
        Main.connect.sendMessage("WeightMonthRequest");
        Main.connect.sendMessage(yearData);
        Main.connect.sendMessage(monthData);
        int days=getMaxDay(yearData,monthData);
        xAxis = new NumberAxis(1, days, 1);
        this.yAxis = new NumberAxis(0, 100, 5);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        int[] height = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("体重/kg");
        xAxis.setLabel("月份");
        lineChart.setTitle("体重变化");
        series.setName("体重");
        for (int i = 1; i <= days; ++i) {
            series.getData().add(new XYChart.Data(i, height[i]));
            System.out.println(height[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }
    @FXML
    void onHeight(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String yearData = year.getText();
        String monthData =month.getText();
        if (yearData.equals("") || monthData.equals("")) {
            return;
        }
        Main.connect.sendMessage("HeightMonthRequest");
        Main.connect.sendMessage(yearData);
        Main.connect.sendMessage(monthData);
        int days=getMaxDay(yearData,monthData);
        xAxis = new NumberAxis(1, days, 1);
        this.yAxis = new NumberAxis(0, 250, 5);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        int[] height = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("身高/cm");
        xAxis.setLabel("月份");
        lineChart.setTitle("身高变化");
        series.setName("身高");
        for (int i = 1; i <= days; ++i) {
            series.getData().add(new XYChart.Data(i, height[i]));
            System.out.println(height[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }
    public int getMaxDay(String yearData,String monthData) throws Exception {
        int year=Integer.parseInt(yearData);
        int month=Integer.parseInt(monthData);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay ;
    }

}
