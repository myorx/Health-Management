package com.example.demo;

import com.example.OBJECT.Health;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Connect
{
    private Socket socket;
    private InputStream inFromServer;
    private OutputStream outToServer;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream inObject;
    private ObjectOutputStream outObject;
    public void connect()
    {
        try
        {
            while (true)
            {
                try
                {
                    socket = new Socket("127.0.0.1", 1234);
                    break;
                }
                catch (Exception e)
                {
                    //System.out.println("连接失败");
                }
            }
            inFromServer = socket.getInputStream();
            outToServer = socket.getOutputStream();
            in = new DataInputStream(inFromServer);
            out = new DataOutputStream(outToServer);
            outObject = new ObjectOutputStream(outToServer);
            inObject = new ObjectInputStream(inFromServer);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnectSuccess.fxml"));
            Stage success = new Stage();
            success.setTitle("连接成功");
            success.setScene(new Scene(loader.load()));
            System.out.println("连接成功");
            success.show();//创建登录成功页面
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public Connect(){
        connect();
    }
    public void sendMessage(String message) throws Exception
    {
        out.writeUTF(message);
        out.flush();
    }
    public String getMessage() throws Exception
    {
        String res = in.readUTF();
        return res;
    }
    public void sendObject(Object object) throws Exception
    {
        outObject.writeObject(object);
        outObject.flush();
    }
    public Object getObject() throws Exception
    {
        Object res = inObject.readObject();
        return res;
    }
}