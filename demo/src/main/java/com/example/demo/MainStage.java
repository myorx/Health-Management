package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainStage {

    public static InquireInformation inquireInformation;
    @FXML
    private Button Exit;

    @FXML
    private Button InformationAnalysis;

    @FXML
    private Button InformationEntry;

    @FXML
    private Button InformationInquiry;

    @FXML
    void onExit(ActionEvent event) {
        ((Stage) Exit.getScene().getWindow()).close();
    }

    @FXML
    void onInformationAnalysis(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnalyseStage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("信息分析");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    void onInformationEntry(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterInformation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("录入信息");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    void onInformationInquiry(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InquireInformation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("查询信息");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
        inquireInformation = fxmlLoader.getController();
    }

}
