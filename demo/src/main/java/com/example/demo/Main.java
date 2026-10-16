package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static Connect connect;//方便全局访问
    public void start(Stage stage) throws Exception
    {
        //创建等待连接页面
        Stage wait=new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("WaitStage.fxml"));
        wait.setTitle("等待连接");
        wait.initStyle(StageStyle.UNDECORATED);
        wait.setScene(new Scene(loader.load()));
        wait.show();
        connect = new Connect();
        wait.close();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}