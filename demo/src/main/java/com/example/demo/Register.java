package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {

    @FXML
    private PasswordField password;
    @FXML
    private Label registerSituation;
    @FXML
    private Button register;

    @FXML
    private TextField username;

    @FXML
    void onRegister(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();
        try {
            Main.connect.sendMessage("registerRequest");
            Main.connect.sendMessage(user);
            Main.connect.sendMessage(pass);
            String result = Main.connect.getMessage();
            if (result.equals("Register successful")) {
                ((Stage) register.getScene().getWindow()).close();
                Stage stage = new Stage();
                //直接创建一个页面，不用fxml文件
                stage.setTitle("注册成功");
                stage.setScene(new Scene(new Label("注册成功")));
                stage.show();
            }
            else {
                registerSituation.setText("注册失败");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
