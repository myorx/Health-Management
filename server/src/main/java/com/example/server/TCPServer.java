package com.example.server;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.OBJECT.Health;
import com.example.OBJECT.ReportData;
import com.google.gson.Gson;
public class TCPServer extends Thread {
    private SQLiteServer sql;
    private InputStream inFromClient;
    private OutputStream outToClient;
    public Socket socket;
    private Connection conn;
    private Statement stmt;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    public TCPServer(Socket socket) throws IOException {
        this.socket = socket;
    }
    public void run() {
        try {
            sql = new SQLiteServer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        action();
    }
    public void action() {
        try {
            inFromClient = socket.getInputStream();
            outToClient = socket.getOutputStream();
            in = new DataInputStream(inFromClient);
            out = new DataOutputStream(outToClient);
            reader = new ObjectInputStream(inFromClient);
            writer = new ObjectOutputStream(outToClient);
            while(true) {
                System.out.println("等待客户端请求");
                String str = getMessage();
                if (str.equals("registerRequest")) {
                    String username = getMessage();
                    String password = getMessage();
                    String result = sql.solveRegister(username, password);
                    sendMessage(result);
                }
                if(str.equals("loginRequest")) {
                    String username = getMessage();
                    String password = getMessage();
                    String result = sql.solveLogin(username, password);
                    sendMessage(result);
                }
                if (str.equals("deleteRequest"))
                {
                    String date = getMessage();
                    sql.solveDelete(date);
                }
                if (str.equals("searchRequest")) {
                    String startYear = getMessage();
                    String startMonth = getMessage();
                    String startDate = getMessage();
                    String endYear = getMessage();
                    String endMonth = getMessage();
                    String endDate = getMessage();
                    Object result = sql.solveSearch(startYear, startMonth, startDate, endYear, endMonth, endDate);
                    sendObject(result);
                }
                if (str.equals("enterRequest")) {
                    String year = getMessage();
                    String month = getMessage();
                    String date = getMessage();
                    String height = getMessage(); if (height.equals("")) height = "0";
                    String weight = getMessage(); if (weight.equals("")) weight = "0";
                    String sleep = getMessage(); if (sleep.equals("")) sleep = "0";
                    String step = getMessage(); if (step.equals("")) step = "0";
                    String result = sql.solveInsert(year, month, date, height, step, sleep, weight);
                    sendMessage(result);
                }
                if (str.equals("changeRequest")) {
                    String Date = getMessage();
                    String height = getMessage(); if (height.equals("")) height = "0";
                    String weight = getMessage(); if (weight.equals("")) weight = "0";
                    String sleep = getMessage(); if (sleep.equals("")) sleep = "0";
                    String step = getMessage(); if (step.equals("")) step = "0";
                    String result = sql.solveChange(Date, height, step, sleep, weight);
                    sendMessage(result);
                }
                if (str.equals("heightAverageRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveHeightAverage(year);
                    sendObject(result);
                }
                if (str.equals("weightAverageRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveWeightAverage(year);
                    sendObject(result);
                }
                if (str.equals("sleepAverageRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveSleepAverage(year);
                    sendObject(result);
                }
                if (str.equals("stepAverageRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveStepAverage(year);
                    sendObject(result);
                }
                if (str.equals("exit")) {
                    System.out.println("客户端请求结束");
                    break;
                }
                if (str.equals("BMIAverageRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveBMIAverage(year);
                    sendObject(result);
                }
                if (str.equals("ReportRequest"))
                {
                    String year = getMessage();
                    Object result = sql.solveReport(year);
                    sendObject(result);
                }
                if (str.equals("StepMonthRequest"))
                {
                    String year = getMessage();
                    String month = getMessage();
                    Object result = sql.solveStepMonth(year,month);
                    sendObject(result);
                }
                if (str.equals("WeightMonthRequest"))
                {
                    String year = getMessage();
                    String month = getMessage();
                    Object result = sql.solveWeightMonth(year,month);
                    sendObject(result);
                }
                if (str.equals("BMIMonthRequest"))
                {
                    String year = getMessage();
                    String month = getMessage();
                    Object result = sql.solveBMIMonth(year,month);
                    sendObject(result);
                }
                if (str.equals("SleepMonthRequest"))
                {
                    String year = getMessage();
                    String month = getMessage();
                    Object result = sql.solveSleepMonth(year,month);
                    sendObject(result);
                }
                if (str.equals("HeightMonthRequest"))
                {
                    String year = getMessage();
                    String month = getMessage();
                    Object result = sql.solveHeightMonth(year,month);
                    sendObject(result);
                }
                if (str.equals("searchPersonRequest"))
                {
                    Object result = sql.solveSearchPerson();
                    sendObject(result);
                }
                if (str.equals("deletePersonRequest"))
                {
                    String person = getMessage();
                    sql.solveDeletePerson(person);
                }
                if (str.equals("searchPersonInformationRequest"))
                {
                    String person = getMessage();
                    String startYear = getMessage();
                    String startMonth = getMessage();
                    String startDate = getMessage();
                    String endYear = getMessage();
                    String endMonth = getMessage();
                    String endDate = getMessage();
                    Object result = sql.solveSearchPersonInformation(person,startYear,startMonth,startDate,endYear,endMonth,endDate);
                    sendObject(result);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendObject(Object object) throws IOException {
        writer.writeObject(object);
        writer.flush();
    }
    public Object getObject() throws IOException, ClassNotFoundException {
        return reader.readObject();
    }
    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
    public String getMessage() throws IOException {
        return in.readUTF();
    }
}