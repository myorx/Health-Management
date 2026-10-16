package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AnalyseStage {

    @FXML
    private Button BMI;

    @FXML
    private Pane basicPane;
    @FXML
    private Button analyseByMonth;

    @FXML
    private Button annualReport;


    @FXML
    private Button height;


    @FXML
    private Button sleepTime;

    @FXML
    private Button step;

    @FXML
    private Button weight;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextField yearEntry;
    @FXML
    void onAnalyseByMonth(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnalyseStageMore.fxml"));
        Stage stage = new Stage();
        stage.setTitle("月度分析");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    void onAnnualReport(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnnualReport.fxml"));
        Stage stage = new Stage();
        stage.setTitle("健康状况报告");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();

    }


    @FXML
    void onBMI(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String year = yearEntry.getText();
        this.xAxis = new NumberAxis(1, 12, 1);
        this.yAxis = new NumberAxis(0, 40, 1);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        Main.connect.sendMessage("BMIAverageRequest");
        Main.connect.sendMessage(year);
        int[] BMIAverage = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("BMI");
        xAxis.setLabel("月份");
        lineChart.setTitle("BMI变化");
        series.setName("BMI");
        for (int i = 1; i <= 12; ++i) {
            series.getData().add(new XYChart.Data(i, BMIAverage[i]));
            System.out.println(BMIAverage[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }

    @FXML
    void onHeight(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String year = yearEntry.getText();
        this.xAxis = new NumberAxis(1, 12, 1);
        this.yAxis = new NumberAxis(0, 200, 5);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        Main.connect.sendMessage("heightAverageRequest");
        Main.connect.sendMessage(year);
        int[] heightAverage = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("身高/cm");
        xAxis.setLabel("月份");
        lineChart.setTitle("身高变化");

        series.setName("身高");
        for (int i = 1; i <= 12; ++i) {
            series.getData().add(new XYChart.Data(i, heightAverage[i]));
            System.out.println(heightAverage[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }

    @FXML
    void onSleep(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String year = yearEntry.getText();
        this.xAxis = new NumberAxis(1, 12, 1);
        this.yAxis = new NumberAxis(0, 24, 1);
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        Main.connect.sendMessage("sleepAverageRequest");
        Main.connect.sendMessage(year);
        int[] sleepAverage = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("睡眠时长/h");
        xAxis.setLabel("月份");
        lineChart.setTitle("睡眠变化");
        series.setName("睡眠");
        for (int i = 1; i <= 12; ++i) {
            series.getData().add(new XYChart.Data(i, sleepAverage[i]));
            System.out.println(sleepAverage[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);

    }

    @FXML
    void onStep(ActionEvent event) throws Exception {
        this.xAxis = new NumberAxis(1, 12, 1);
        this.yAxis = new NumberAxis(0, 50000, 1000);
        basicPane.getChildren().clear();
        String year = yearEntry.getText();
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        Main.connect.sendMessage("stepAverageRequest");
        Main.connect.sendMessage(year);
        int[] stepAverage = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("步数");
        xAxis.setLabel("月份");
        lineChart.setTitle("步数");
        series.setName("步数");
        for (int i = 1; i <= 12; ++i) {
            series.getData().add(new XYChart.Data(i, stepAverage[i]));
            System.out.println(stepAverage[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);


    }

    @FXML
    void onWeight(ActionEvent event) throws Exception {
        basicPane.getChildren().clear();
        String year = yearEntry.getText();
        this.xAxis = new NumberAxis(1, 12, 1);
        this.yAxis = new NumberAxis(0, 100, 5);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        Main.connect.sendMessage("weightAverageRequest");
        Main.connect.sendMessage(year);
        int[] weightAverage = (int[]) Main.connect.getObject();
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        lineChart.getData().add(series);
        yAxis.setLabel("体重/kg");
        xAxis.setLabel("月份");
        lineChart.setTitle("体重变化");
        series.setName("体重");
        for (int i = 1; i <= 12; ++i) {
            series.getData().add(new XYChart.Data(i, weightAverage[i]));
            System.out.println(weightAverage[i]);
        }
        lineChart.getData().add(series);
        basicPane.getChildren().add(lineChart);


    }


}
