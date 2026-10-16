package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminStage {

    @FXML
    private Button Exit;

    @FXML
    private Button manage;

    @FXML
    void onExit(ActionEvent event) {
        ((Stage) Exit.getScene().getWindow()).close();
    }

    @FXML
    void onManage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageStage.fxml"));
        Stage stage = new Stage();
        stage.setTitle("管理界面");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

}
