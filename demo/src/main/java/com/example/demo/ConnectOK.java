package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConnectOK
{

    @FXML
    private Button confirm;

    @FXML
    void confirmOK(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 600, 400));
        stage.setTitle("Login");
        stage.show();
    }
}
