package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeInformation {
    @FXML
    private Label date;

    @FXML
    private TextField height;

    @FXML
    private Button save;

    @FXML
    private TextField sleep;

    @FXML
    private TextField step;

    @FXML
    private TextField weight;
    public void ChangeRequest(String date, String height, String weight, String sleepHours, String stepNumber) throws Exception {
        Main.connect.sendMessage("changeRequest");
        Main.connect.sendMessage(date);
        Main.connect.sendMessage(height);
        Main.connect.sendMessage(weight);
        Main.connect.sendMessage(sleepHours);
        Main.connect.sendMessage(stepNumber);
        Alert alert;
        if (Main.connect.getMessage().equals("false")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("修改失败！");
        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("修改成功！");
        }
        alert.show();
        MainStage.inquireInformation.searchRequest("", "", "","","","");
    }
    @FXML
    void onSave(ActionEvent event) throws Exception {
        String date = this.date.getText();
        String height = this.height.getText();
        String weight = this.weight.getText();
        String sleepHours = this.sleep.getText();
        String stepNumber = this.step.getText();
        ChangeRequest(date, height, weight, sleepHours, stepNumber);
        this.height.setText("");
        this.weight.setText("");
        this.sleep.setText("");
        this.step.setText("");
    }
    public void init(String date) {
        this.date.setText(date);
    }
}
