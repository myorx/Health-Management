package com.example.demo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login
{
    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    void loginRequest(String user, String pass) throws Exception
    {
        try
        {
            Main.connect.sendMessage("loginRequest");
            Main.connect.sendMessage(user);
            Main.connect.sendMessage(pass);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    String getRequest() throws Exception
    {
        return Main.connect.getMessage();
    }
    @FXML
    void onLogin(ActionEvent event)
    {
        String user = username.getText();
        String pass = password.getText();
        try
        {
            loginRequest(user, pass);
            if (getRequest().equals("true"))
            {
                System.out.println("Login successful");
                ((Stage) login.getScene().getWindow()).close();
                if (user.equals("admin"))
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminStage.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("管理员界面");
                    stage.setScene(new Scene(loader.load()));
                    stage.show();
                    return;
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainStage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("健康信息管理");
                stage.setScene(new Scene(loader.load()));
                stage.show();
            }
            else
            {
                System.out.println("Login failed");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFailed.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void onRegister(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Stage stage = new Stage();
        stage.setTitle("注册");
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
