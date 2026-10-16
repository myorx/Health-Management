package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EnterInformation {

    @FXML
    private TextField date;

    @FXML
    private Button determineEnter;

    @FXML
    private TextField height;

    @FXML
    private TextField month;

    @FXML
    private Button reset;

    @FXML
    private TextField sleepHours;

    @FXML
    private TextField stepNumber;

    @FXML
    private TextField weight;

    @FXML
    private TextField year;

    public String enterRequest(String year, String month, String date, String height, String weight, String sleepHours, String stepNumber) {
        try {
            Main.connect.sendMessage("enterRequest");
            Main.connect.sendMessage(year);
            Main.connect.sendMessage(month);
            Main.connect.sendMessage(date);
            Main.connect.sendMessage(height);
            Main.connect.sendMessage(weight);
            Main.connect.sendMessage(sleepHours);
            Main.connect.sendMessage(stepNumber);
            return Main.connect.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }
    @FXML
    void onDetermineEnter(ActionEvent event) {
        String year = this.year.getText();
        String month = this.month.getText();
        String date = this.date.getText();
        String height = this.height.getText();
        String weight = this.weight.getText();
        String sleepHours = this.sleepHours.getText();
        String stepNumber = this.stepNumber.getText();
        String res = enterRequest(year, month, date, height, weight, sleepHours, stepNumber);
        Alert alert;
        if (res.equals("true")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("录入成功！");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("录入失败！");
        }
        alert.show();
        this.year.clear();
        this.month.clear();
        this.date.clear();
        this.height.clear();
        this.weight.clear();
        this.sleepHours.clear();
        this.stepNumber.clear();
    }
    @FXML
    void onReset(ActionEvent event) {
        year.clear();
        month.clear();
        date.clear();
        height.clear();
        weight.clear();
        sleepHours.clear();
        stepNumber.clear();
    }

}
